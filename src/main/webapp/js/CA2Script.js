$(document).ready( function() {
    
    $("#goToPerson").click(function() {
        $("#ca2Content").load("personApp.html");
    })
    $("#goToCompany").click(function() {
        $("#ca2Content").load("companyApp.html");
    })
    
})