/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
nextFocus = (function (id) {
    var tecla = (event.keyCode ? event.keyCode : event.which);
    // 13 = enter, 9 = tab
    console.log(tecla);
    if ((tecla == 13 || tecla == 9)) {
        event.preventDefault();
        PrimeFaces.widgets[id].jq.focus();
        return false;
    } else {
        return true;
    }

});

popup = (function (pagina) {
    window.open(pagina, '_blank', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=850,height=700');
});

relatorio = (function () {
    window.setTimeout("abrePopup(pagina)", 1000);
});


function mDown(obj) {
    obj.style.background = "#1ec5e5";
}

function mUp(obj) {
    obj.styleSheet.
            obj.innerHTML = "Thank You";
}

var setarFocu = function (id) {
    PrimeFaces.widgets[id].jq.focus();
};








function setee(div) {

//    var todosPacientes = document.getElementById("tabela");
    console.log(div.className);
//    for(var posicaoAtual = 0; posicaoAtual <= todosPacientes.length - 1; posicaoAtual++){
//        console(todosPacientes[posicaoAtual].id);
//    }
////    
//    var div = document.getElementById('btnLogin');
//

    div.backgroundColor = 'red';
}
