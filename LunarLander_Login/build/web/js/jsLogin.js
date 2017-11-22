//al cargar por completo la página...
window.onload = function () {

    $("#butLogin").click(function () {
        loginUser();
    });
    $("#butReg").click(function () {
        $("#modalRegistro").modal({backdrop: "static", keyboard: "false"});
    });
    $("#btnCancel").click(function () {
        $("#modalRegistro").modal('hide');
        $("#regName").val("");
        $("#regUser").val("");
        $("#regPwd").val("");
        $("#regPwd2").val("");
    });
    $("#btnReg").click(function () {
        registerUser();
    });
};

//Definición de funciones
function loginUser() {
    if (loginValid()) {
        var username = $("#logUser").val();
        var pwd = $("#logPwd").val();
        var url = "LoginUser";
        var emess = "Error desconocido";
        $.ajax({
            method: "POST",
            url: url,
            data: {username: username, password: pwd},
            success: function (u) {
                if (u["mess"] === "La contraseña introducida es erronea") {
                    $("#logPwd").focus();
                    alert(u["mess"]);
                }
                if (u["mess"] === "El usuario introducido no existe") {
                    $("#logUser").focus();
                    alert(u["mess"]);
                }
                location.reload();

            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });
    }
}

function loginValid() {
    if ($("#logUser").val() === "") {
        $("#logUser").focus();
        alert("Por favor, introduce tu nombre de usuario");
        return false;
    }
    if ($("#logPwd").val() === "") {
        $("#logPwd").focus();
        alert("Por favor, introduce tu contraseña");
        return false;
    }
    return true;
}



function registerUser() {
    if (registerValid()) {
        var name = $("#regName").val();
        var username = $("#regUser").val();
        var pwd = $("#regPwd").val();
        var url = "RegUser";
        var emess = "Error desconocido";
        $.ajax({
            method: "POST",
            url: url,
            data: {nombre: name, username: username, password: pwd},
            success: function (u) {
                if (u["mess"] === "El nombre de usuario ya está en uso") {
                    $("#regUser").focus();
                } else {
                    $("#modalRegistro").modal('hide');
                    $("#regName").val("");
                    $("#regUser").val("");
                    $("#regPwd").val("");
                    $("#regPwd2").val("");
                }
                alert(u["mess"]);
            },
            error: function (e) {
                if (e["responseJSON"] === undefined)
                    alert(emess);
                else
                    alert(e["responseJSON"]["error"]);
            }
        });
    }
}

function registerValid() {
    if ($("#regName").val() === "") {
        $("#regName").focus();
        alert("Por favor, introduce tu nombre");
        return false;
    }
    if ($("#regUser").val() === "") {
        $("#regUser").focus();
        alert("Por favor, introduce tu nombre de usuario");
        return false;
    }
    if ($("#regPwd").val() === "") {
        $("#regPwd").focus();
        alert("Por favor, introduce una contraseña");
        return false;
    }
    if ($("#regPwd").val() !== $("#regPwd2").val()) {
        $("#regPwd").focus();
        alert("Las contraseñas no cinciden");
        return false;
    }
    return true;
}
