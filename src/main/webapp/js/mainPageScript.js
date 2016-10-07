$(document).ready(function () {

    $('.myMenu ul li').hover(function () {
        $(this).children('ul').stop(true, false, true).slideToggle(300);
    });
    
    $("#mainContent").load("AboutUs.html");
    
    $("#aboutUs").click(function() {
        $("#mainContent").load("AboutUs.html");
    })
    
    $("#addPerson").click(function() {
        $("#mainContent").load("webPerson/addPerson.html");
    })
    
    $("#docRestApi").click(function() {
        
        $("#mainContent").load("webDocumentation/restAPI.html");
    })
    
    $("#docErrorResponses").click(function() {
        
        $("#mainContent").load("webDocumentation/errorResponses.html");
    })
    
    $("#docJsonFormat").click(function() {
        
        $("#mainContent").load("webDocumentation/jsonFormat.html");
    })
    
    $("#unitTest").click(function() {
        
        $("#mainContent").load("webDocumentation/test/unitTest.html");
    })
    
    $("#facadeTest").click(function() {
        
        $("#mainContent").load("webDocumentation/test/testDBFacade.html");
    })
    
    $("#restTest").click(function() {
        
        $("#mainContent").load("webDocumentation/test/testRestAPI.html");
    })
    
    $("#whoDidWhat").click(function() {
        
        $("#mainContent").load("webDocumentation/whoDidWhat.html");
    })
    
    $("#inheritance").click(function() {
        
        $("#mainContent").load("webDocumentation/inheritance.html");
    })
    
    $("#howToDemo").click(function() {
        
        $("#mainContent").load("webDocumentation/howToDemo.html");
    })
    
    $("#getPerson").click(function() {
        
        $("#mainContent").load("webPerson/getPerson.html");
    })
    
    $("#getCompany").click(function() {
        
        $("#mainContent").load("webCompany/getCompany.html");
    })
    
    $("#createCompany").click(function() {
        
        $("#mainContent").load("webCompany/addCompany.html");
    })
})
