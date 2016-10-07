$(document).ready(function () {

    $("#addPhone").click(function () {
        $("#phoneAdded").append('<div id="phoneDiv">Phonenumber : <input class="phone#" type="text" placeholder="add new number"> Phonedesciption : <input class="phoneDesc" type="text" placeholder="add phone desciption"><br></div>');
    })

    $("#submitCompany").click(function () {

        console.log("submitting company")
        var phones = new Array;
        $('#phones :input').each(function () {
            var attr = $(this).val();
            phones.push(attr);
        });
        var phoneList = new Array;
        for (var i = 0; i < phones.length; i++) {
            if (i % 2 === 0) {
                var phone = {
                    number: phones[i],
                    description: phones[i + 1]
                }
                phoneList.push(phone);
            }
        }

        var temp = $('#cityinfo').val();
        var tempArray = temp.split('-');

        var cityInfo = {
            ZIP: tempArray[0],
            CITY: tempArray[1]
        }

        console.log(cityInfo);
        console.log(tempArray.length);
        console.log(temp);

        var address = {
            Street: $("#address").val(),
            AdditionalInfo: $("#addInfo").val(),
            cityInfo: cityInfo
        }

        var Company = {
            name: $("#companyname").val(),
            description: $("#description").val(),
            cvr: $("#cvr").val(),
            NumEmployees: $("#numEmployees").val(),
            marketValue: $("#marketValue").val(),
            phones: phoneList,
            email: $("#email").val(),
            address: address
        }

        $.ajax({
            type: "POST",
            url: "api/company/createNew",
            data: JSON.stringify(Company),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) {
                $("#mainContent").text($("#companyname").val() + " " + $("#description").val() + ",    " +
                        "CVR: " + $("#cvr").val() + ",    " + "number of employees: " + $("#numEmployees").val() + ",    " +
                        " email: " + $("#email").val() + ",    address: " + address.Street + " - "
                        + address.AdditionalInfo + " , " + cityInfo.CITY + " - " + cityInfo.ZIP
                        + " -     has been successfully added to the database");
                console.log("success");
            },
            error: function (jqXHR, status) {
                // error handler
                console.log(jqXHR);
                console.log('fail' + status.code);
            }
        });
    })

    $("load", function () {
        $.ajax({
            type: "GET",
            url: "api/otherInfo",
            data: false,
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (obj) {
                var array = obj;
                var option = '';
                for (var i = 0; i < array.length; i++) {
                    option += '<option value="' + array[i].ZIP + '-' + array[i].CITY + '">' + array[i].ZIP + "-" + array[i].CITY + '</option>';
                }
                $('#cityinfo').append(option);
            },
            error: function (jqXHR, status) {
                // error handler
                console.log(jqXHR);
                console.log('fail' + status.code);
            }
        });
    });

})