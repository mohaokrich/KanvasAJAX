//import { Tarea } from "./tarea";

//ARRAY DE TAREAS
let arrayTareas = new Array();
//TOKEN DE VALIDACION
var csrfToken = $("[name='_csrf']").attr("content");
//OBTNER TODAS LAS TAREAS EN EL INDEX
function obtenerTareas() {
	//let resultados = document.getElementById("idbody");
	//resultados.replaceChildren();
	let enlace = window.location.href;
	let contenedor = enlace.split("/");
	let idProyecto = contenedor[5];
	fetch('/tareas/' + idProyecto, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(tareas => {

			arrayTareas.push(tareas);
			pintarTareas(arrayTareas[0]);
			console.log(arrayTareas);

		});
}

//
var click = 0;
function misTareas() {
	click++;
	let columna1 = document.getElementsByClassName("columna")[0];
	let columna2 = document.getElementsByClassName("columna")[1];
	let columna3 = document.getElementsByClassName("columna")[2];
	let columna4 = document.getElementsByClassName("columna")[3];
	if (click === 1) {
		var resultsByCreator = arrayTareas[0].filter(function(tarea) { return tarea.id_usuario == 11; });//-->obtiene todos los elementos con el mismo id;
		columna1.replaceChildren();
		columna2.replaceChildren();
		columna3.replaceChildren();
		columna4.replaceChildren();
		pintarTareas(resultsByCreator);
		//var firstObj = (results.length > 0) ? results[0] : null;
	} else if (click === 2) {
		columna1.replaceChildren();
		columna2.replaceChildren();
		columna3.replaceChildren();
		columna4.replaceChildren();
		pintarTareas(arrayTareas[0]);
		click = 0;
	}
}
//FUNCION PARA IMPRIMIR LAS TAREAS.
function pintarTareas(listaTareas) {
	for (let tarea of listaTareas) {
		anadirTarjeta(tarea);
	}
}

//OBTENER INFO TAREA
$(document).on('click', '#getInfo', function() {
	//obtener id tarea del tag <a> (EJEMP. TSK-4)
	var divPadre = $(this).closest(".card-body");
	var divHijo = divPadre[0].childNodes[0];
	var contentOfaHijo = divHijo.childNodes[1].innerText;
	let contenedor = contentOfaHijo.split("-");
	let idTareaCard = contenedor[1];
	//obtener el elemento del array donde el id de la tarea sea igual al id del tag	<a>
	var results = arrayTareas[0].filter(function(tarea) { return tarea.idTarea == idTareaCard; });//-->obtiene todos los elementos con el mismo id;
	var firstObj = (results.length > 0) ? results[0] : null;//-->obtiene el primer elemento con el mismo id (el id es unico en cada elemento);

	let divInfoTarea = document.getElementById("info-tarea");
	divInfoTarea.replaceChildren();

	//crearElemento(input, tipo, id, clase, valor,color)//-->metodo para crear elementos html
	let tituloId = crearElemento("h6", "", "titId", "", "ID", "blue");
	let id = crearElemento("h6", "", "idText", "", firstObj.idTarea, "");

	let tituloTitle = crearElemento("h6", "", "titTitle", "", "TITULO", "blue");
	let titulo = crearElemento("h6", "", "tituloText", "", firstObj.titulo, "");

	let tituloEstado = crearElemento("h6", '', "titEstado", '', 'ESTADO', "blue");
	let estado = crearElemento("h6", "", "estadoText", "", firstObj.estado, "");

	let tituloPrioridad = crearElemento("h6", '', "titPrioridad", '', 'PRIORIDAD', "blue");
	let prioridad = crearElemento("h6", "", "prioridadText", "", firstObj.prioridad, "");

	let tituloDescripcion = crearElemento("h6", '', "titDescripcion", '', 'DESCRIPCION', "blue");
	let descripcion = crearElemento("h6", "", "descripcionText", "", firstObj.descripcion, "");


	let buttonDelete = crearElemento("button", "button", "borrarTarea", "btn btn-danger btn-sm", "BORRAR TAREA", "");

	divInfoTarea.appendChild(tituloId);
	divInfoTarea.appendChild(id);

	divInfoTarea.appendChild(tituloTitle);
	divInfoTarea.appendChild(titulo);

	divInfoTarea.appendChild(tituloEstado);
	divInfoTarea.appendChild(estado);

	divInfoTarea.appendChild(tituloPrioridad);
	divInfoTarea.appendChild(prioridad);

	divInfoTarea.appendChild(tituloDescripcion);
	divInfoTarea.appendChild(descripcion);

	divInfoTarea.appendChild(buttonDelete);

	rellenarModal(firstObj);

});

//RELLENAR INPUTS DEL MODAL
function rellenarModal(tarea) {
	$("#inputNombreEditar").val(tarea.titulo);
	$("#inputDescripcionEditar").val(tarea.descripcion);
	$("#inputPrioridadEditar").val(tarea.prioridad);
	$("#inputTrabajadoresEditar").val(tarea.id_usuario);
}

//AÑADIR DOM DE TARJETA E INSERTARLA EN EL ROOT DEL INDEX
function anadirTarjeta(tarea) {
	let divCard = document.createElement("div");
	divCard.classList.add("card", "draggable", "shadow-sm");
	divCard.setAttribute("id", "tsk-" + tarea.idTarea);
	divCard.setAttribute("draggable", "true");
	divCard.setAttribute("ondragstart", "drag(event)");

	let divCardBody = document.createElement("div");
	divCardBody.classList.add("card-body", "p-2");


	let divCardTitle = document.createElement("div");
	divCardTitle.classList.add("card-title");


	let span = document.createElement("span");
	span.classList.add("badge", "rounded-pill");
	span.textContent = tarea.prioridad;

	if (tarea.prioridad == "BAJA") {
		span.classList.add("bg-primary");
	} else if (tarea.prioridad == "MEDIA") {
		span.classList.add("bg-warning");
	} else if (tarea.prioridad == "ALTA") {
		span.classList.add("bg-danger");
	}


	let link = document.createElement("a");
	link.classList.add("lead", "font-weight-light");
	link.textContent = "TSK-" + tarea.idTarea;

	let link1 = document.createElement("a");
	link1.classList.add("lead", "font-weight-light");
	link1.setAttribute("style", "text-align: right;float: right;");
	link1.textContent = tarea.nombre_usuario;



	let para = document.createElement("p");
	para.textContent = tarea.descripcion;



	let button = document.createElement("button");
	button.classList.add("btn", "btn-primary", "btn-sm");
	button.setAttribute("onclick", "$('#editar_modal').show()");
	button.setAttribute("id", "getInfo");
	button.textContent = "View";


	divCardTitle.appendChild(span);
	divCardTitle.appendChild(link);
	divCardTitle.appendChild(link1);

	divCardBody.appendChild(divCardTitle);
	divCardBody.appendChild(para);
	divCardBody.appendChild(button);

	divCard.appendChild(divCardBody);

	let divDropZone = document.createElement("div");
	divDropZone.setAttribute("id", "tsk" + tarea.idTarea);
	divDropZone.classList.add("dropzone", "rounded");
	divDropZone.setAttribute("ondrop", "drop(event)");
	divDropZone.setAttribute("ondragover", "allowDrop(event)");
	divDropZone.setAttribute("ondragleave", "clearDrop(event)");
	divDropZone.setAttribute("style", "widht:181.967px;height:24px;");

	let columna1 = document.getElementsByClassName("columna")[0];
	let columna2 = document.getElementsByClassName("columna")[1];
	let columna3 = document.getElementsByClassName("columna")[2];
	let columna4 = document.getElementsByClassName("columna")[3];


	if (tarea.estado == "Preparada") {
		columna1.appendChild(divCard);
		columna1.appendChild(divDropZone);
	} else if (tarea.estado == "En Curso") {
		columna2.appendChild(divCard);
		columna2.appendChild(divDropZone);
	} else if (tarea.estado == "En revisión") {
		columna3.appendChild(divCard);
		columna3.appendChild(divDropZone);
	} else if (tarea.estado == "Finalizada") {
		columna4.appendChild(divCard);
		columna4.appendChild(divDropZone);
	}

}
//CREAR TAREA
function crearTarea() {
	let enlace = window.location.href;
	let contenedor = enlace.split("/");
	let idProyecto = contenedor[5];

	console.log(csrfToken);

	let nombreTarea = $('#inputNombre').val();
	let nombrePrioridad = $('#inputPrioridad').val();
	let nombreDescripcion = $('#inputDescripcion').val();


	if (nombreTarea != "" && nombrePrioridad != "" && nombreDescripcion != "") {
		fetch('/tarea/crear/' + idProyecto, {
			headers: {
				credentials: 'same-origin',
				"Content-Type": "application/json; charset=utf-8",
				'X-CSRF-TOKEN': csrfToken
			},
			method: 'POST',
			body: JSON.stringify({
				titulo: $('#inputNombre').val(),
				prioridad: $('#inputPrioridad').val(),
				descripcion: $('#inputDescripcion').val(),
				//usuario: $('#inputTrabajadores').val(),
			})
		})
			.then(function(response) {
				if (response.ok) {
					return response.json()

				} else {
					throw "Error";
				}

			}).then(res => {
				//añadimos la tarea al array
				let arrayInstance = arrayTareas[0];
				arrayInstance.push(res);
				//pintamos el array en el dom
				anadirTarjeta(res);
				//vaciamos el form
				limpiarFormulario();
				//cerramos el modal
				cerrarModal('#crear_modal');
			}).catch(function() {
                let errorDiv = document.getElementById("errorDiv1");
                let div = document.createElement('div');
                div.classList.add("alert", "alert-dismissible", "alert-danger");
                let button = document.createElement('button');
                button.classList.add("btn-close");
                button.setAttribute("data-bs-dismiss","alert");
                let link = document.createElement('a');
                link.classList.add("alert-link");
                link.setAttribute("href","#");
                var linkText = document.createTextNode("Error al crear tarea");
                link.appendChild(linkText);
                div.appendChild(button);
                div.appendChild(link);
                var textNode = document.createTextNode("Prueba mas tarde");
                div.appendChild(textNode);
                errorDiv.appendChild(div);
        });
	};
}

//EDITAR TAREA
function editarTarea() {
	var idTarea = document.getElementById("idText").textContent;
	console.log(csrfToken);

	let nombreTarea = $('#inputNombreEditar').val();
	let prioridadTarea = $('#inputPrioridadEditar').val();
	let usuarioTarea = $('#inputTrabajadoresEditar').val();
	let descripcionTarea = $('#inputDescripcionEditar').val();

	if (nombreTarea != "" && prioridadTarea != "" && usuarioTarea != "" && descripcionTarea != "") {
		fetch('/editar/tarea' + idTarea, {
			headers: {
				credentials: 'same-origin',
				"Content-Type": "application/json; charset=utf-8",
				'X-CSRF-TOKEN': csrfToken
			},
			method: 'PUT',
			body: JSON.stringify({
				titulo: $('#inputNombreEditar').val(),
				prioridad: $('#inputPrioridadEditar').val(),
				descripcion: $('#inputDescripcionEditar').val(),
				usuario: $('#inputTrabajadoresEditar').val(),
			})

		})
			.then(function(response) {
				if (response.ok) {
					return response.json()
				} else {
					throw "Error";
				}

			}).then(res => {
				console.log(res);
				iterarColumnasGuardarCambios(res);
				cerrarModal('#editar_modal');
			});
	};
}
//FUNCION PARA CERRAR EL MODAL
function cerrarModal(nombreModal) {
	  $(nombreModal).modal('toggle');
	  $(nombreModal).modal('toggle');
}
function iterarColumnasGuardarCambios(tarea) {
	//MODIFICAMOS LOS VALORES DEL DOM
	let columna1;
	switch (tarea.estado) {
		case 'Preparada':
			columna1 = document.getElementsByClassName("columna")[0];
			break;
		case 'En Curso':
			columna1 = document.getElementsByClassName("columna")[1];
			break;
		case 'En revisión':
			columna1 = document.getElementsByClassName("columna")[2];
			break;
		case 'Finalizada':
			columna1 = document.getElementsByClassName("columna")[3];
			break;
	}
	for (var i = 0, columna; columna = columna1.childNodes[i]; i++) {
		var primerDivCol = columna.nextElementSibling;
		if (primerDivCol.childNodes.length != 0) {
			var segundoDivCol = primerDivCol.childNodes[0];
			let arrayHijosDiv2 = segundoDivCol.childNodes;
			var pDescripcion = arrayHijosDiv2[1];
			var cardTitle = arrayHijosDiv2[0];
			let arrayHijosTitle = cardTitle.childNodes;
			var spanPrioridad = arrayHijosTitle[0];
			var aNombreUsuario = arrayHijosTitle[2];
			var aIdTarea = arrayHijosTitle[1].innerText;
			let contenedor = aIdTarea.split("-");
			let id = contenedor[1];
			if (id == tarea.idTarea) {
				pDescripcion.textContent = tarea.descripcion;
				aNombreUsuario.innerText = tarea.nombre_usuario
				iterarPrioridad(tarea, spanPrioridad);
				break;
			}
		} else {
			continue;
		}
	}
	//MODIFICAMOS LOS DATOS DEL ARRAY
	var results = arrayTareas[0].filter(function(tareaArray) { return tareaArray.idTarea == tarea.idTarea; });//-->obtiene todos los elementos con el mismo id;
	var firstObj = (results.length > 0) ? results[0] : null;//-->obtiene el primer elemento con el mismo id (el id es unico en cada elemento);

	firstObj.descripcion = tarea.descripcion;
	firstObj.titulo = tarea.titulo;
	firstObj.prioridad = tarea.prioridad;

	console.log(firstObj)
}
//FUNCION PARA CAMBIAR EL COLOR DE LA PRIORIDAD AL ITERAR CADA CARTA
function iterarPrioridad(tarea, spanPrioridad) {

	if (tarea.prioridad == "BAJA") {
		spanPrioridad.removeAttribute("class")
		spanPrioridad.setAttribute("class", "badge rounded-pill bg-primary");
		spanPrioridad.textContent = tarea.prioridad;
	} else if (tarea.prioridad == "MEDIA") {
		spanPrioridad.removeAttribute("class")
		spanPrioridad.setAttribute("class", "badge rounded-pill bg-warning");
		spanPrioridad.textContent = tarea.prioridad;
	} else if (tarea.prioridad == "ALTA") {
		spanPrioridad.removeAttribute("class")
		spanPrioridad.setAttribute("class", "badge rounded-pill bg-danger");
		spanPrioridad.textContent = tarea.prioridad;
	}
}
//FUNCION PARA BORRAR TAREA
$(document).on('click', '#borrarTarea', function() {
	//obtener id tarea del tag <a> (EJEMP. TSK-4)

	let idTarea = document.getElementById("idText").innerText;

	var dropzone = document.getElementById("tsk" + idTarea);
	var dropzone1 = document.getElementById("tsk-" + idTarea);
	//var dropzone1 = $(this).closest(".draggable");
	//obtener el elemento del array donde el id de la tarea sea igual al id del tag	<a>
	var results = arrayTareas[0].filter(function(tarea) { return tarea.idTarea == idTarea; });//-->obtiene todos los elementos con el mismo id;
	var firstObj = (results.length > 0) ? results[0] : null;//-->obtiene el primer elemento con el mismo id (el id es unico en cada elemento);


	fetch("/borrar/tarea" + idTarea, {
		headers: { "Content-Type": "application/json; charset=utf-8" }

	}).then(function(response) {
		if (response.ok) {
			removeItemFromArr(arrayTareas[0], firstObj);
			console.log(arrayTareas);
			dropzone.remove();
			dropzone1.remove();
			cerrarModal('#editar_modal');
		} else {
			throw "Error";
		}

	});
});
//FUNCION PARA ELMINAR UN ELEMENTO DEL ARRAY
function removeItemFromArr(arr, item) {
	var i = arr.indexOf(item);
	if (i !== -1) {
		arr.splice(i, 1);
	}
}
//FUNCION que dado los parámetros devolverá una una elmeneto del DOM con todos los datos de la tarea.
function crearElemento(input, tipo, id, clase, valor, color) {
	let elemento = document.createElement(input);
	elemento.setAttribute("type", tipo);
	elemento.setAttribute("id", id);
	elemento.setAttribute("class", clase);
	elemento.setAttribute("value", valor);
	elemento.textContent = valor;
	elemento.style.color = color;
	return elemento;
}
document.addEventListener("DOMContentLoaded", function() {
	obtenerTareas();
	$("#addTarea").click(crearTarea);
	$("#flexSwitchCheckChecked").click(misTareas);
});

//PREVENT DEFAULT PARA EL FORMULARIO
document.getElementById("form-crear").addEventListener("click", function(event){
  event.preventDefault()
});
function limpiarFormulario() {
	document.getElementById("inputNombre").value = '';
	document.getElementById("inputPrioridad").value = '';
	document.getElementById("inputDescripcion").value = '';
}