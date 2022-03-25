import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Profesor } from '../models/Profesor';
import { map, catchError,tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {

  private url: string = 'http://localhost:8080/api/profesores';

  constructor(private http : HttpClient,private router: Router) { }


  getAll():Observable<Profesor[]>{
    return this.http.get<Profesor[]>(this.url);
  }

  getProfesor(id:number):Observable<Profesor>{
    return this.http.get<Profesor>(this.url + '/' + id)
    .pipe(
      catchError((e) => {
        this.router.navigate(['/profesores']);
        console.error(e.error.mensaje);
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  saveProfesor(profesor: Profesor):Observable<Profesor>{
    return this.http.post<Profesor>(this.url,profesor)
    .pipe(
      map((response: any) => response.profesor as Profesor),
      catchError((e) => {
        if (e.status==400) {
          return throwError(e);
        }
        console.error(e.error.mensaje);
        Swal.fire('No se pudo crear',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  updateProfesor(profesor: Profesor):Observable<Profesor>{
    return this.http.put<Profesor>(this.url+'/'+profesor.id_profesor,profesor)
    .pipe(
      map((response: any) => response.profesor as Profesor),
      catchError((e) => {
        if (e.status==400) {
          return throwError(e);
        }
        console.error(e.error.mensaje);
        Swal.fire('No se pudo actualizar',e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  deleteProfesor(id:number):Observable<Profesor>{
    return this.http.delete<Profesor>(this.url+'/'+id)
    .pipe(
      catchError((e) => {
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}
