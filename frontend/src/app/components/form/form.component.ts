import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profesor } from 'src/app/models/Profesor';
import { ProfesorService } from 'src/app/services/profesor.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  profesor:Profesor= new Profesor();
  titulo:string="CRUD Profesores";
  errores:string[]=[];
  constructor(private profesorService:ProfesorService, private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarProfesor();

  }

  createProfesor():void{
    this.profesorService.saveProfesor(this.profesor).subscribe(
      res=>{
        this.router.navigate(['/profesores'])
        Swal.fire('Nuevo registro',`Profesor ${res.nombre} creado con éxito`,'success')
      },
      err=>{
        this.errores = err.error.errors as string[];
        console.error(err);
        console.error('Código del error desde el backend: '+err.status);
        console.error(err.error.errors);
      }
    );
  }

  updateProfesor():void{
    this.profesorService.updateProfesor(this.profesor).subscribe(
      res=> {
        this.router.navigate(['/profesores']);
        Swal.fire('Registro editado',`Profesor ${res.nombre} actualizado con éxito`,'success')
      },
      err=>{
        this.errores = err.error.errors as string[];
        console.error(err);
        console.error('Código del error desde el backend: '+err.status);
        console.error(err.error.errors);
      }
    );
  }

  cargarProfesor():void{
    this.activatedRoute.params.subscribe(
      e=>{
        let id=e['id'];
        if (id) {
          this.profesorService.getProfesor(id).subscribe(
            es=>this.profesor=es

          );

        }

      }
    );

  }
}
