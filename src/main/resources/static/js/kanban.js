const drag = (event) => {
	event.dataTransfer.setData("text/plain", event.target.id);
}

const allowDrop = (ev) => {
	ev.preventDefault();
	if (hasClass(ev.target, "dropzone")) {
		addClass(ev.target, "droppable");
	}

}

const clearDrop = (ev) => {
	removeClass(ev.target, "droppable");
}

const drop = (event) => {
	event.preventDefault();
	const data = event.dataTransfer.getData("text/plain");
	const element = document.querySelector(`#${data}`);

	let contenedor = data.split("-");
	let idTarea = contenedor[1];

	let eventoLocalizado = event.currentTarget;
	
	let columna = eventoLocalizado.parentElement;
	
	let estado = columna.attributes[1].value;
	try {
		// remove the spacer content from dropzone
		event.target.removeChild(event.target.firstChild);
		// add the draggable content
		event.target.appendChild(element);
		// remove the dropzone parent
		unwrap(event.target);
		//Cambiamos el estado de la base de datos
		cambiarEstado(idTarea, estado);
	} catch (error) {
		console.warn("can't move the item to the same place")
	}
	updateDropzones();
}
//FUNCION PARA CAMBIAR EL ESTADO DE LA TAREA AL MOVERLA A OTRA COLUMNA
function cambiarEstado(idtarea, stateValue) {
	fetch('/editEstado/tarea' + idtarea, {
		headers: {
			credentials: 'same-origin',
			"Content-Type": "application/json; charset=utf-8",
			'X-CSRF-TOKEN': csrfToken
		},
		method: 'PUT',
		body: JSON.stringify({
				estado: stateValue
		})

	})
		.then(function(response) {
			if (response.ok) {
				return response.json()
			} else {
				throw "Error";
			}
		}).catch(function() {
                let errorDiv = document.getElementById("errorDiv");
                let div = document.createElement('div');
                div.classList.add("alert", "alert-dismissible", "alert-danger");
                let button = document.createElement('button');
                button.classList.add("btn-close");
                button.setAttribute("data-bs-dismiss","alert");
                let link = document.createElement('a');
                link.classList.add("alert-link");
                link.setAttribute("href","#");
                var linkText = document.createTextNode("Error de conexion");
                link.appendChild(linkText);
                div.appendChild(button);
                div.appendChild(link);
                var textNode = document.createTextNode("prueba mas tarde");
                div.appendChild(textNode);
                errorDiv.appendChild(div);
        });
}



const updateDropzones = () => {
	/* after dropping, refresh the drop target areas
	  so there is a dropzone after each item
	  using jQuery here for simplicity */

	var dz = $('<div class="dropzone rounded" ondrop="drop(event)" ondragover="allowDrop(event)" ondragleave="clearDrop(event)"> &nbsp; </div>');

	// delete old dropzones
	$('.dropzone').remove();

	// insert new dropdzone after each item   
	dz.insertAfter('.card.draggable');

	// insert new dropzone in any empty swimlanes
	$(".items:not(:has(.card.draggable))").append(dz);
};

// helpers
function hasClass(target, className) {
	return new RegExp('(\\s|^)' + className + '(\\s|$)').test(target.className);
}

function addClass(ele, cls) {
	if (!hasClass(ele, cls)) ele.className += " " + cls;
}

function removeClass(ele, cls) {
	if (hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
}

function unwrap(node) {
	node.replaceWith(...node.childNodes);
}



function despuesDesoltar(event) {
	event.preventDefault();
	var ele = this;
	var lista = ele.parentNode;
	var h6 = lista.parentNode.childNodes[1];


	console.log(this.id + "  " + h6.textContent);


}

function cerrarModal() {
	let self = $(this);
	let ventana = self.closest(".modal");
	ventana.hide();
}



document.addEventListener('DOMContentLoaded', function() {

	$('.draggable').on('dragend', despuesDesoltar);


})

