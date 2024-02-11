import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, RouterStateSnapshot,UrlTree, Router, CanActivate } from "@angular/router";
import { Observable } from "rxjs";

@Injectable({
    providedIn:'root'
})

export class MonsterDetailGuard implements CanActivate {

    constructor(private router: Router){}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
            const id = Number(route.paramMap.get('id'));
            if(isNaN(id) || id < 1){
                alert('Invalid monster id');
                this.router.navigate(['/monsters']);
                return false;
            }
            return true;
        }
}