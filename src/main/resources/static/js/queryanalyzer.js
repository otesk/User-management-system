$(document).ready(function (){
    $("#searchForm").submit(function (){
        if ($("#usernameFilter").val()===''){
            $("#usernameFilter").remove();
        }
    })
})
