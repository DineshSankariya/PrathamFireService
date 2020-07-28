$(document).ready(function(){

    $('[data-toggle="tooltip"]').tooltip();
    $('.container_line-2_row-limit').change(function(){

        var limit=$(this).val();

        $('.container_line-2_row-limit:selected').removeAttr("selected");
        $(this).attr("selected");
        window.location.href="?page="+0+"&size="+limit;


    });

    $('.search_input').on('keyup keydown',function(){

        search_data($(this).val());

    });

    function search_data(data){
                    var alert='false';
                    var num=0;
                    $('.mytable tbody tr').each(function(){
                        var found='false';
                        $(this).each(function(){
                          if($(this).text().toLowerCase().indexOf(data.toLowerCase())>=0){
                            found='true'
                          }
                        });

                        if(found=='true'){
                            $(this).show();

                            alert='true';
                        }else{
                            $(this).hide();

                        }
                    });

                    if(alert=='false'){

                        if($('.container_invoice_table .mydiv').length){

                        }else{

                            $('.container_invoice_table').prepend("<div class=\"alert alert-danger mydiv\" > No data found </div>");
                            $('.container_invoice_table .mydiv').css({"width":"300px","font-size":"16px"});

                        }
                    }else{
                        if($('.container_invoice_table .mydiv').length){
                            $('.container_invoice_table .mydiv').remove();
                        }
                    }

                    if(!data){
                         $('.container_invoice_table .mydiv').remove();
                         $('.container_invoice_table .mydiv-1').remove();
                    }

         }

           $('.add').click(function(event){

              $("input[name='c_name']").val("");
              $("input[name='c_al_name']").val("");
              $("input[name='p_name']").val("");
              $("input[name='p_desg']").val("");
              $("input[name='b_title']").val("");
              $("input[name='c_email']").val("");
              $("input[name='c_contact']").val("");
              $("input[name='gst']").val("");
              $("#myTextarea").val("");
              $("input[name='c_post']").val("");
              $("input[name='c_city']").val("");
              $("input[name='c_state']").val("");
              $("input[name='c_post']").val("");
              $("input[name='isactive']:checked").prop("checked",true);
              $(".save").val("save");
             $("#exampleModal").modal();

         });

         $('.save').click(function(){

             var c_name=$("input[name='c_name']").val();
             var c_al_name= $("input[name='c_al_name']").val();
             var p_name= $("input[name='p_name']").val();
             var p_desg= $("input[name='p_desg']").val();
             var b_title= $("input[name='b_title']").val();
             var c_email= $("input[name='c_email']").val();
             var c_contact= $("input[name='c_contact']").val();
             var c_address= $("#myTextarea").val();
             var c_post= $("input[name='c_post']").val();
             var c_city= $("input[name='c_city']").val();
             var c_state= $("input[name='c_state']").val();
             var c_post= $("input[name='c_post']").val();
             var gst=$("input[name='gst']").val();
             var isactive= $("input[name='isactive']:checked").val();

             data={

                 "c_name":c_name,
                 "c_alias_name":c_al_name,
                 "p_name":p_name,
                 "p_designation":p_desg,
                 "gst_num":gst,
                 "b_title":b_title,
                 "c_email":c_email,
                 "c_contact":c_contact,
                 "c_address":c_address,
                 "c_post":c_post,
                 "city":c_city,
                 "state":c_state,
                 "postal_code":c_post,
                 "isactive":isactive,

             };

             var url='';
             var httptype='';
             if($('.save').val()=="Update"){
                var c_id=$("input[name='c_id']").val();
                data.id=c_id;

                url='/clientrest/updateclient';
                httptype='PUT'

             }else{
                url='/clientrest/saveclient';
                httptype='POST'
             }


             datatopass={"client":data};

             $.ajax({
                 contentType: 'application/json;charset=UTF-8',
                 url:url,
                 type: httptype,
                 data: JSON.stringify(data),
                 dataType: 'json',
                 success:function(data){

                     if(data.success=="ok"){
                         var alert_success="<div id='success_save'> Saved successfully </p>";
                          $('.container_invoice_table').prepend(alert_success);
                          $('#success_save').css({"width":"500px","margin-left":"10px","font-size":"15px","display":"inline-block"});
                          $('#success_save').addClass("alert alert-success");

                         setTimeout(function(){

                             setTimeout(function(){
                                 $('#success_save').remove();
                              },3500);
                            $('#exampleModal').toggle();
                            window.location.reload();
                         },1000);

                     }

                 },
                 error:function(){
                     console.log("Error");
                 }

             });


     });

     $('.mybadge_edit').click(function(event){
         event.preventDefault();
         var id=$(this).attr("href").split("/");
         var url=$(this).attr("href").split("?");
         var id=parseInt(id[3]);


         $.ajax({
                 contentType: 'application/json;charset=UTF-8',
                 url:'/clientrest/find/'+id,
                 success:function(data){
                    $("input[name='c_id']").val(data["id"]);
                     var c_name=$("input[name='c_name']").val(data["c_name"]);
                     var c_al_name= $("input[name='c_al_name']").val(data["c_alias_name"]);
                     var p_name= $("input[name='p_name']").val(data["p_name"]);
                     var p_desg= $("input[name='p_desg']").val(data["p_designation"]);
                     var b_title= $("input[name='b_title']").val(data["b_title"]);
                     var c_email= $("input[name='c_email']").val(data["c_email"]);
                     var c_contact= $("input[name='c_contact']").val(data["c_contact"]);
                     var c_address= $("#myTextarea").val(data["c_address"]);
                     var c_post= $("input[name='c_post']").val(data["postal_code"]);
                     var c_city= $("input[name='c_city']").val(data["city"]);
                     var c_state= $("input[name='c_state']").val(data["state"]);
                     var gst=$("input[name='gst']").val(data["gst_num"]);

                     if(data["isactive"]==true){
                            $(".div-1 .div-1_radio-2 input[name='isactive']").removeAttr("checked");
                            $(".div-1 .div-1_radio-1 input[name='isactive']").attr("checked",true);
                      }else{

                            $(".div-1 .div-1_radio-1 input[name='isactive']").removeAttr("checked");
                            $(".div-1 .div-1_radio-2 input[name='isactive']").attr("checked",true);
                        }
                    $(".save").val("Update");
                    $("#exampleModal").modal();
                 }

             });


     });

     $('.mybadge_delete').click(function(event){

         event.preventDefault();
         var id=$(this).attr("href").split("/");
         $('.company_id').text(id[3]);
         var id_todelete=parseInt($('.delete_id').val(id[3]));
         $("#delete_invoice").modal();

    });

     $('.delete_btn').click(function(){
         event.preventDefault();
         var id=$('.delete_id').val();
         del(id);
    })


      function del(data){
         $.ajax({

             contentType: 'application/json;charset=UTF-8',
             url:'/clientrest/delete_client/'+data,
             success:function(data){
                     if(data=="ok"){
                         var alert_success="<div id='success'> Deleted successfully </div>";
                         $('.delete_footer').prepend(alert_success);
                         $('#success').css({"max-width":"200px","margin-right":"90px"});
                         $('#success').addClass("alert alert-success");
                         setTimeout(function(){
                             window.location.reload();
                          }, 1500);
                    }
              }

          });

      }


});