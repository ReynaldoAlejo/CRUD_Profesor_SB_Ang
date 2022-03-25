import { Component, OnInit } from '@angular/core';
import { Profesor } from 'src/app/models/Profesor';
import { ProfesorService } from 'src/app/services/profesor.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profesor',
  templateUrl: './profesor.component.html',
  styleUrls: ['./profesor.component.css']
})
export class ProfesorComponent implements OnInit {

  titulo:string="CRUD Profesores";
  profesores:Profesor[]=[];
  experiencia:string="años";

  constructor(private profesorService: ProfesorService,) { }

  ngOnInit(): void {
    this.profesorService.getAll().subscribe(
      e=> this.profesores=e
    );
  }

  deleteProfesor(profesor:Profesor):void{

    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar al profesor ${profesor.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!',
      cancelButtonText: 'No, cancelar!',
    }).then((result) => {
      if (result.isConfirmed) {

        this.profesorService.deleteProfesor(profesor.id_profesor).subscribe(
          res=>{
            this.profesorService.getAll().subscribe(
            response=>this.profesores=response);
            Swal.fire(
              'Registro eliminado',
              `Profesor ${profesor.nombre} eliminado con éxito `,
              'success'
            )
            }
        );
      }
    })



  }

}
