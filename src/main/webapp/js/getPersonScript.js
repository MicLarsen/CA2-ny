$(document).ready(function () {

    $("#loadPersonByPhone").click(function () {
        var someImage = $("#listWrapper");
        var imageText = $("#imageText");
        someImage.html("");
        imageText.html("");
        someImage.append('We did not have time to connect Entity with Persons phonenumbers correct, so this function does not work ...');
        imageText.append('<img src="pictures/construction.gif" alt=""/>');
    })

    $("#loadPersonByHobby").click(function () {
        var hobbySelected = $('#hobbyList').val();
        console.log(hobbySelected);
        $.ajax({
            type: "GET",
            url: "api/person/hobby/" + hobbySelected,
            data: JSON.stringify(hobbySelected),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (obj) {

                var persons = new Array();
                persons.push(["id", "Firstname", "Lastname", "Address", "Add-Info", ""]);

                for (var i = 0; i < obj.length; i++) {
                    var link = "<a href=\"#\" onclick=\"deletePerson();\" id=\"delete\">delete</a>&nbsp/&nbsp<a href=\"#\" onclick=\"editPerson();\" id=\"edit\">edit</a>";
                    persons.push([obj[i].id + ". ", obj[i].firstName, obj[i].lastName,
                        obj[i].address.Street, obj[i].address.AdditionalInfo, link]);
                }
                var table = $("<table id=\"listOfPersons\"/>");
                table[0].border = "1";
                var columnCount = persons[0].length;
                var row = $(table[0].insertRow(-1));
                for (var i = 0; i < columnCount; i++) {
                    var headerCell = $("<th />");
                    headerCell.html(persons[0][i]);
                    row.append(headerCell);
                }

                for (var i = 1; i < persons.length; i++) {
                    row = $(table[0].insertRow(-1));
                    for (var j = 0; j < columnCount; j++) {
                        if (j % 2 === 0) {

                            var cell = $("<td class=\"even\" id=\"" + j + "\"/>");
                            cell.html(persons[i][j]);
                            row.append(cell);
                        } else if (j % 2 !== 0) {
                            var cell = $("<td class=\"odd\" id=\"" + j + "\"/>");
                            cell.html(persons[i][j]);
                            row.append(cell);
                        }
                    }
                }
                var dvTable = $("#listWrapper");
                dvTable.html("");
                dvTable.append(table);

            },
            error: function (jqXHR, status) {
                console.log(jqXHR);
                console.log('fail' + status.code);
            }
        });

    })

    $("load", function () {
        $.ajax({
            type: "GET",
            url: "api/person/hobbyList",
            data: false,
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (obj) {
                var option = '';
                for (var i = 0; i < obj.length; i++) {
                    option += '<option value="' + obj[i] + '">' + obj[i] + '</option>';
                }
                $('#hobbyList').append(option);
            },
            error: function (jqXHR, status) {
                console.log(jqXHR);
                console.log('fail' + status.code);
            }
        });
    });

    $("#loadSinglePerson").click(function () {
        $("#imageText").text("");
        var id = $("#id").val();
        $("#listWrapper").text("");
        $.ajax({
            type: "GET",
            url: "api/person/" + id,
            data: false, // now data come in this function
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (obj) {

                var persons = new Array();
                persons.push(["id", "Firstname", "Lastname", "Address", "Add-Info", ""]);
                var link = "<a href=\"#\" onclick=\"deletePerson();\" id=\"delete\">delete</a>&nbsp/&nbsp<a href=\"#\" onclick=\"editPerson();\" id=\"edit\">edit</a>";
                persons.push([obj.id + ". ", obj.firstName, obj.lastName,
                    obj.address.Street, obj.address.AdditionalInfo, link]);
                var table = $("<table id=\"listOfPersons\"/>");
                table[0].border = "1";
                var columnCount = persons[0].length;
                var row = $(table[0].insertRow(-1));
                for (var i = 0; i < columnCount; i++) {
                    var headerCell = $("<th />");
                    headerCell.html(persons[0][i]);
                    row.append(headerCell);
                }

                for (var i = 1; i < persons.length; i++) {
                    row = $(table[0].insertRow(-1));
                    for (var j = 0; j < columnCount; j++) {
                        if (j % 2 === 0) {

                            var cell = $("<td class=\"even\" id=\"" + j + "\"/>");
                            cell.html(persons[i][j]);
                            row.append(cell);
                        } else if (j % 2 !== 0) {
                            var cell = $("<td class=\"odd\" id=\"" + j + "\"/>");
                            cell.html(persons[i][j]);
                            row.append(cell);
                        }
                    }
                }
                var dvTable = $("#listWrapper");
                dvTable.html("");
                dvTable.append(table);
            },
            error: function (jqXHR) {
                $("#listWrapper").load("errorPage.html", function () {

                    $("#errorText").text("an Error occured while looked for id: " + id);
                    $("#errorCode").text("status code: " + jqXHR.status);
                    $("#responseType").text("looking for : " + jqXHR.responseJSON);
                    $("#documentation").text("where : " + jqXHR.statusText);
                });
            }
        });
    })

    $("#loadFullList").click(function () {
        $("#imageText").text("");
        $("#listWrapper").text("");
        $.ajax({
            type: "GET",
            url: "api/person/all",
            data: false, // now data come in this function
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (obj) {

                var persons = new Array();
                persons.push(["id", "Firstname", "Lastname", "Address", "Add-Info", ""]);

                for (var i = 0; i < obj.length; i++) {
                    var link = "<a href=\"#\" onclick=\"deletePerson();\" id=\"delete\">delete</a>&nbsp/&nbsp<a href=\"#\" onclick=\"editPerson();\" id=\"edit\">edit</a>";
                    persons.push([obj[i].id + ". ", obj[i].firstName, obj[i].lastName,
                        obj[i].address.Street, obj[i].address.AdditionalInfo, link]);
                }
                var table = $("<table id=\"listOfPersons\"/>");
                table[0].border = "1";
                var columnCount = persons[0].length;
                var row = $(table[0].insertRow(-1));
                for (var i = 0; i < columnCount; i++) {
                    var headerCell = $("<th />");
                    headerCell.html(persons[0][i]);
                    row.append(headerCell);
                }

                for (var i = 1; i < persons.length; i++) {
                    row = $(table[0].insertRow(-1));
                    for (var j = 0; j < columnCount; j++) {
                        if (j % 2 === 0) {

                            var cell = $("<td class=\"even\" id=\"" + j + "\"/>");
                            cell.html(persons[i][j]);
                            row.append(cell);
                        } else if (j % 2 !== 0) {
                            var cell = $("<td class=\"odd\" id=\"" + j + "\"/>");
                            cell.html(persons[i][j]);
                            row.append(cell);
                        }
                    }
                }
                var dvTable = $("#listWrapper");
                dvTable.html("");
                dvTable.append(table);
            },
            error: function (jqXHR, status) {
                $("#listWrapper").load("errorPage.html", function () {

                    $("#errorText").text("an Error occured while looked for id: " + id);
                    $("#errorCode").text("status code: " + jqXHR.status);
                    $("#responseType").text("looking for : " + jqXHR.responseJSON);
                    $("#documentation").text("where : " + jqXHR.statusText);
                });
            }
        });
    })
})

function editPerson() {
    $('#listOfPersons tr').click(function () {
        var id = $(this).closest('tr').find('td:eq(0)').text();
        console.log("id is: " + id);
        var fName = $(this).closest('tr').find('td:eq(1)').text();
        var lName = $(this).closest('tr').find('td:eq(2)').text();
        var address = $(this).closest('tr').find('td:eq(3)').text();
        var addInfo = $(this).closest('tr').find('td:eq(4)').text();
        $("#listWrapper").load("webPerson/editPerson.html", function () {
            var idField = $("#idField");
            idField.html("");
            idField.append(id);
            $("#fName").attr("placeholder", fName);
            $("#lName").attr("placeholder", lName);
            $("#address").attr("placeholder", address);
            $("#addInfo").attr("placeholder", addInfo);
        });
    });
}

$("#submitChanges").click(function () {
    var fName = "";
    var lName = "";
    var phone = "";
    var firstName = $("#fName").val();
    var lastName = $("#lName").val();
    var phoneNumber = $("#phone").val();
    var id = $("#id").html();
    console.log(id);
    if (firstName === "") {
        fName = $("#fName").attr("placeholder");
    } else {
        fName = firstName;
    }
    if (lastName === "") {
        lName = $("#lName").attr("placeholder");
    } else {
        lName = lastName;
    }
    if (phoneNumber === "") {
        phone = $("#phone").attr("placeholder");
    } else {
        phone = phoneNumber;
    }

    var jsonObject = '{"id": ' + id + ', "fName": ' + fName + ', "lName": ' + lName + ',"phone": ' + phone + '}';
    console.log(id);
    $.ajax({
        type: "PUT",
        url: "http://localhost:8084/RESTAssignment/api/person/edit/?object=" + jsonObject,
        data: JSON.stringify(jsonObject), // now data come in this function
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        dataType: "json",
        success: function (data, status, jqXHR) {

            console.log("success"); // write success in " "
        },
        error: function (jqXHR, status) {
            // error handler
            console.log(jqXHR);
            console.log('fail' + status.code);
        }
    });
})


function deletePerson() {
    $('#listOfPersons tr').click(function () {
        var id = $(this).closest('tr').find('td:eq(0)').text();
        $.ajax({
            type: "DELETE",
            url: "api/person/delete/?id=" + id,
            data: JSON.stringify(id), // now data come in this function
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) {

                console.log("success"); // write success in " "
            },
            error: function (jqXHR, status) {
                // error handler
                console.log(jqXHR);
                console.log('fail' + status.code);
            }
        });
    });
}


