/* 发送ajax请求，用于测试 */
function test() {
    $("#pi*").text("");
    $.ajax({
        type: "post",
        url: 'test',
        data: {hostPort: $("#hostPort").val()},
        datatype: 'json',
        success: function (json) {
            if (json != "") {
                $("#dataTit").text("return");
                $("#dataRes").text(json.state);
                $("#dataMsg").text(json.msg);
            }
        }
    });
}

function dispRes(name, time, testHeader) {
    $("#" + name + "Tit").text(name);
    $("#" + name + "Tim").text(time);
    $("#" + name + "Res").text(testHeader);
}

function login() {
    // alert($("#userId").val());
    sessionStorage.setItem("userId", $("#userId").val());
    sessionStorage.setItem("userName", $("#userName").val());
    sessionStorage.removeItem("userInfo");
}
function logout() {
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("userName");
    sessionStorage.removeItem("userInfo");
    $("#userId").val("");
    $("#userName").val("");
}

function makeError() {
    throw new Error($("#errormsg").val());
}
