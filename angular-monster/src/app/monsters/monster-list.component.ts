import { Component, OnDestroy, OnInit } from '@angular/core';
import { IMonster } from './monster';
import { MonsterService } from './monster.service';
import { Subscription } from 'rxjs';

@Component({    
    templateUrl: './monster-list.component.html',
    styleUrls: ['./monster-list.component.css']
})

export class MonsterListComponent implements OnInit, OnDestroy {
    pageTitle = 'Monster List';
    imageWidth = 50;
    imageMargin = 2;
    showImage = false;
    private _listFilter: string = '';
    filteredMonsters: IMonster[] = [];
    errorMessage: string = '';
    sub!: Subscription;
    

    constructor(private monsterService: MonsterService){}

    get listFilter(): string{
        return this._listFilter;
    }

    set listFilter(value: string){
        this._listFilter = value;
        console.log('In setter:', value);
        this.filteredMonsters = this.performFilter(value);
    }

    performFilter(filterBy: string): IMonster[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.monsters.filter((monster: IMonster) => monster.monsterName.toLocaleLowerCase().includes(filterBy));
    }

    monsters: IMonster[] = [];


    toggleImage(): void {
        this.showImage = !this.showImage;
    }

    ngOnInit(): void {
      this.sub = this.monsterService.getMonsters().subscribe({
        next: monsters => {
          this.monsters = monsters;
          this.filteredMonsters = this.monsters;
        },    
        error: err => this.errorMessage = err
      });    
         
    }

    ngOnDestroy(){
      this.sub.unsubscribe();
    }

    onRatingClicked(message: string): void{
      this.pageTitle = 'Monster List: ' + message;
    }
}