(function () {
    var url = `ws://${document.location.host + document.location.pathname}chat`;

    ws = new WebSocket(url);

    console.log(url)

    var mensajes = document.getElementById('conversacion');
    var boton = document.getElementById('btnenviar');
    var ingresar = document.getElementById('btningresar');
    var nombre = document.getElementById('nombre');
    var mensaje = document.getElementById('message');

    ws.onopen = function (e) {
        if (e.isTrusted) {
            console.log("Conectado");
            boton.setAttribute("disabled", "true");
        }
    }

    ws.onmessage = function (e) {
        console.log(e);
        if(e.isTrusted){
            var obj = JSON.parse(e.data);
            if(obj.nombre != nombre.value){
                mensajes.innerHTML += `<li class="other"> <p>${obj.nombre}</p> ${obj.mensaje}</li>`;
            }else{
                mensajes.innerHTML += `<li class="main"> <p>${obj.nombre}</p> ${obj.mensaje}</li>`;
            }
        }
    }

    boton.addEventListener('click', enviar);

    function enviar() {
        if (mensaje.value != "") {
            msg = `{"nombre": "${nombre.value}", "mensaje": "${mensaje.value}"}`;
            ws.send(msg);
            mensaje.value = "";
        }
    }

    ingresar.addEventListener('click', function () {
        if (nombre.value != "") {
            msg = `{"nombre": "${nombre.value}", "mensaje": "Ingreso al chat"}`;
            ws.send(msg);
            ingresar.setAttribute("style", "display: none;");
            nombre.setAttribute("style", "display: none;");
            boton.removeAttribute("disabled");
        } else {
            alert("Introduce un nombre");
        }
    });

    function onMessage(evt) {
        var obj = JSON.parse(evt.data);
        msg = obj.nombre + ' dice: ' + obj.mensaje;
        mensajes.innerHTML += '<br/>' + msg;
    }
})();