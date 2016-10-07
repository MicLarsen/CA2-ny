$(document).ready(function () {

    $("#loadSimpelList").click(function () {

        console.log("clicking simpelList button");

        $.ajax({
            type: "GET",
            url: "api/person/1",
            data: false, // now data come in this function
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (obj) {
                
                console.log(obj);
                var array = obj;

                $(obj).each(function(index) {
                    console.log(obj[index]);
                })
//                var persons = new Array();
//                persons.push(["id", "Firstname", "Lastname", "Phone", ""]);
//                for (var i = 0; i < array.length; i++) {
//                    var link = "<a href=\"#\" onclick=\"deletePerson();\" id=\"delete\">delete</a>&nbsp/&nbsp<a href=\"#\" onclick=\"editPerson();\" id=\"edit\">edit</a>";
//                    persons.push([array[i].id, array[i].fName, array[i].lName,
//                        array[i].phone, link]);
//                }
//
//                var table = $("<table id=\"listOfPersons\"/>");
//                table[0].border = "1";
//                var columnCount = persons[0].length;
//                var row = $(table[0].insertRow(-1));
//                for (var i = 0; i < columnCount; i++) {
//                    var headerCell = $("<th />");
//                    headerCell.html(persons[0][i]);
//                    row.append(headerCell);
//                }
//
//                for (var i = 1; i < persons.length; i++) {
//                    row = $(table[0].insertRow(-1));
//                    for (var j = 0; j < columnCount; j++) {
//                        if (j % 2 === 0) {
//
//                            var cell = $("<td class=\"even\" id=\"" + j + "\"/>");
//                            cell.html(persons[i][j]);
//                            row.append(cell);
//                        } else if (j % 2 !== 0) {
//                            var cell = $("<td class=\"odd\" id=\"" + j + "\"/>");
//                            cell.html(persons[i][j]);
//                            row.append(cell);
//                        }
//                    }
//                }
//
//                var dvTable = $("#listWrapper");
//                dvTable.html("");
//                dvTable.append(table);
            },
            error: function (jqXHR, status) {
                // error handler
                console.log(jqXHR);
                console.log('fail' + status.code);
            }
        });


    })

    $("#loadFullList").click(function () {
        console.log("clicking fulllList button");
        $("#listWrapper").text("clicking fullList button");
    })

})

