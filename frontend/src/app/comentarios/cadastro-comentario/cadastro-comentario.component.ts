import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';

@Component({
  selector: 'app-cadastro-comentario',
  templateUrl: './cadastro-comentario.component.html',
  styleUrls: ['./cadastro-comentario.component.css']
})
export class CadastroComentarioComponent implements OnInit {
  idComentario: string;
  idPontoTuristico: string;
  formComentario: FormGroup;
  title: string = "Novo cadastro de comentário"
  constructor(private formBuilder: FormBuilder,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private http: HttpService
		) { 

		this.formComentario = this.formBuilder.group({
			comentario: ['', Validators.compose([Validators.required])],
			autor: ['', Validators.compose([Validators.required])],
			dtComentario: [''],
      idPontoTuristico: ['']
		})
	}

  ngOnInit(): void {
    this.idComentario = this.route.snapshot.paramMap.get('idComentario');
    this.idPontoTuristico = this.route.snapshot.paramMap.get('idPontoTuristico');
    
    if(this.idComentario != undefined && this.idComentario != null && this.idComentario != "") {
      this.buscaDadosComentario()
      this.title = "Cadastro do Comentário"
    }
    
    
  }

  salvar(){
    this.formComentario.value.dtComentario = this.obterDataAtual();
    this.formComentario.value.idPontoTuristico = this.idPontoTuristico;
		if (this.validarRegistro()){
			if (this.idComentario == null || this.idComentario != undefined || this.idComentario == ""){
				this.enviarPost();
			} else {
				this.enviarPut();
			}
		} else {
			this.poNotification.error("Preencha todos os campos antes de salvar as alterações!")
		}
	}

  obterDataAtual() {
    const date = new Date();

    const ano = date.getFullYear();
    const mes = date.getMonth();
    const dia = date.getDate();

    let mesValor = '';
    let diaValor = '';

    mesValor = ((mes < 10) ? '0' : '').concat(mes.toString())
    diaValor = ((dia < 10) ? '0' : '').concat(dia.toString())

    return ano.toString().concat('-').concat(mesValor).concat('-').concat(diaValor);
}

	voltar(){
		this.router.navigate(['/ponto-turistico/' + this.idPontoTuristico + '/comentario'], { relativeTo: this.route })
	}

	validarRegistro(): boolean{
		return this.formComentario.valid;
	}

  enviarPost(){
		this.http.post('ponto-turistico/' + this.idPontoTuristico + '/comentario',this.formComentario.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro criado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

	enviarPut(){
		this.http.put('ponto-turistico/' + this.idPontoTuristico + '/comentario/' + this.idComentario,this.formComentario.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

  buscaDadosComentario(){
		this.http.get('ponto-turistico/' + this.idPontoTuristico + '/comentario/' + this.idComentario).subscribe({
			next: (resposta)=>{
				this.formComentario.patchValue({
					autor: resposta.autor,
					comentario: resposta.comentario
				})
			},
			error: (erro)=>{
				this.poNotification.error(erro)
			}
		})
	}
}
