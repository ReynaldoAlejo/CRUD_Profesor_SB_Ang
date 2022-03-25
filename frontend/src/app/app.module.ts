import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { Routes,RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { ProfesorComponent } from './components/profesor/profesor.component';
import { FormComponent } from './components/form/form.component';
import { FormsModule } from '@angular/forms';

const routes:Routes=[
  {path:'', redirectTo:'/profesores', pathMatch:'full'},
  {path:'profesores' ,component:ProfesorComponent},
  {path:'profesores/form' ,component:FormComponent},
  {path:'profesores/form/:id' ,component:FormComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    ProfesorComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
