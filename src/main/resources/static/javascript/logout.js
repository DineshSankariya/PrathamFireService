$(document).ready(function(){

    $(".logout").on("click",function(){

//        window.location.href="/logout";
        var value=$(this).val();
        console.log(value);
        if(value!="Log out"){
            console.log("is not logout");
            $.ajax({

                url:'/user/profile',
                type:'GET',
                success:function(data){
                    console.log(data);
                }

            });
        }else{
            console.log("is logout");
            window.location.href="/logout";
        }

    })



})