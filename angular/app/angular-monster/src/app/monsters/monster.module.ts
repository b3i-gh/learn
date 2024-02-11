import { NgModule } from '@angular/core';
import { MonsterListComponent } from './monster-list.component';
import { MonsterDetailComponent } from './monster-detail.component';
import { RouterModule } from '@angular/router';
import { MonsterDetailGuard } from './monster-detail.guard';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    MonsterListComponent,
    MonsterDetailComponent
  ],
  imports: [
    RouterModule.forChild([
      { path: 'products', component: MonsterListComponent},
      { path: 'products/:id',
        canActivate: [MonsterDetailGuard],
        component: MonsterDetailComponent
      },
    ]),
    SharedModule
  ]
})
export class MonsterModule { }
