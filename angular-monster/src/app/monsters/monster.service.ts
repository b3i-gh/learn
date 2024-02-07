import { Injectable } from "@angular/core";
import { IMonster } from "./monster";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable, catchError, tap, throwError } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class MonsterService{
    private monstersUrl = 'api/monsters/monsters.json';

    constructor(private http: HttpClient){}

    getMonsters(): Observable<IMonster[]>{
        return this.http.get<IMonster[]>(this.monstersUrl).pipe(
            tap(data => console.log('All: ', JSON.stringify(data))),
            catchError(this.handleError)
        );
    }

    private handleError(err: HttpErrorResponse){
        let errorMessage ='';
        if(err.error instanceof ErrorEvent){
            errorMessage = `An error occurred: ${err.error.message}`;
        } else {
            errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        console.error(errorMessage);
        return throwError(()=>errorMessage);
    }
}