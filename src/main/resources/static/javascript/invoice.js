  $(document).ready(function(){
            var glob='false';

            $('[data-toggle="tooltip"]').tooltip();
            $('.container_line-2_row-limit').change(function(){

                var limit=$(this).val();
                window.location.href="?page="+0+"&size="+limit;
                setTimeout(function(){

                  $('.container_line-2_row-limit:selected').removeAttr("selected");
                  $(this).attr("selected");

                },1500);

            });

            $('.search_input').on('keyup keydown',function(){

                search_data($(this).val());

            });

            $('.add').click(function(event){

                event.preventDefault();
                var date=$("input[name='date']").val("");
                var bank=$("input[name='exampleRadios']:checked").prop("checked",false);
                var payment=$(".div-5 input[name='exampleRadios1']:checked").prop("checked",false);
                var item=$('.div-4_item').val("");
                var code=$('.code').val("");
                var capacity=$('.capacity').val("");
                var rate=$('.rate').val("");
                var quantity=$('.nos').val("");
                var price=$('.amount').val("");
                 $(".save").val("save");
                $('.mycheckbox').prop("checked",false);
                $("#exampleModal").modal();
            });

            $('.save').click(function(){

                var date=$("input[name='date']").val();
                var bank=$("input[name='exampleRadios']").val();
                var payment=$(".div-5 input[name='exampleRadios1']:checked").val();
                var client=$(".div-3_select option:selected").val();
                var item=$('.div-4_item').val();
                var code=$('.code').val();
                var capacity=$('.capacity').val();
                var rate=$('.rate').val();
                var quantity=$('.nos').val();
                var price=$('.amount').val();

                var flag='false';
                if(price){
                    flag='true';
                }

                if(flag){



                    $.ajax({
                        contentType: 'application/json;charset=UTF-8',
                        url:'/clientrest/find/'+client,
                        success:function(data){
                                   var x={
                                           "id":data["id"],
                                           "c_name":data["c_name"],
                                           "c_alias_name":data["c_alias_name"],
                                           "p_name":data["p_name"],
                                           "p_designation":data["p_designation"],
                                           "b_title":data["b_title"],
                                           "c_email":data["c_email"],
                                           "c_contact":data["c_contact"],
                                           "gst_num":data["gst_num"],
                                           "c_address":data["c_address"],
                                           "postal_code":data["postal_code"],
                                           "city":data["city"],
                                           "state":data["state"],
                                           "isactive":data["isactive"],

                                   };

                                   data={
                                       "bank":bank,
                                       "item":item,
                                       "code":code,
                                       "capacity":parseInt(capacity),
                                       "rate":parseFloat(rate),
                                       "nos":parseInt(quantity),
                                       "amount":parseFloat(price),
                                       "date":date,
                                       "payment":payment,
                                       "client":x
                                   };

                                   var url='';
                                    var httptype='';
                                    if($('.save').val()=="Update"){
                                       var inv_id=$("input[name='i_id']").val();
                                       data.id=inv_id;

                                       url='/invoicerest/updateinvoice';
                                       httptype='PUT'

                                    }else{
                                       url='/invoicerest/saveinvoice';
                                       httptype='POST'
                                    }

                                  var datatopass={"invoice":data};
                                  console.log(datatopass);
                                        $.ajax({
                                            contentType: 'application/json;charset=UTF-8',
                                            url:url,
                                            type: httptype,
                                            data:JSON.stringify(data),
                                            dataType: 'json',
                                            success:function(data){

                                                if(data.success=="ok"){

                                                    var alert_success="<div id='success_save'> Saved successfully </p>";
                                                    $('.add_invoice_header .modal-title').append(alert_success);
                                                     $('#success_save').css({"width":"500px","margin-left":"10px","font-size":"15px","display":"inline-block"});
                                                     $('#success_save').addClass("alert alert-success");
                                                    setTimeout(function(){
                                                         window.location.reload();
                                                    }, 1500);


                                                }

                                            },
                                            error:function(){
                                                console.log("hello");
                                            }

                                        });
                              }
                      });
                 }else{
                    $('.amount').css("border-color","red");
                 }


            });

            $('.mybadge_edit').click(function(event){
                event.preventDefault();
                var id=$(this).attr("href").split("=");
                var url=$(this).attr("href").split("?");

                var id=parseInt(id[1]);


                $.ajax({
                        contentType: 'application/json;charset=UTF-8',
                        url:'/invoicerest/find/'+id,
                        success:function(data){

                             $.ajax({

                              contentType: 'application/json;charset=UTF-8',
                              url:'/clientrest/find_client/'+id,
                              success:function(data){
                                $(".div-3_select ").val(data["id"]);
                              }

                             });

                            $("input[name=\"i_id\"]").val(data.id);
                            $(".div-5 input[name=\"exampleRadios1\"]:checked").prop('checked',false);
                            $(".div-1 input[name=\"exampleRadios\"]:checked").prop('checked',false);
                            $("input[name='date']").val(data.date);
                            $('.div-4_item').val(data.item);
                            $('.code').val(data.code);
                            $('.capacity').val(data.capacity);
                            $('.rate').val(data.rate);
                            $('.nos').val(data.nos);
                            $('.amount').val(data.amount);
                             $('.mycheckbox').prop("checked",true);
                             $(".save").val("Update");
                             var banks=$("input[name='exampleRadios']");

                             $(".div-1 input[name='exampleRadios']").each(function(){
                                var check=$(this);
                                 if(check.val()===data.bank){

                                    if(data.bank==="BOB"){
                                        $(".div-1 .div-1_radio-1 input[name='exampleRadios']").prop("checked",true);
                                    }else{
                                        $(".div-1 .div-1_radio-2 input[name='exampleRadios']").prop("checked",true);
                                    }


                                 }
                             });

                              $(".div-5 input[name='exampleRadios1']").each(function(){

                                 var check=$(this);
                                 if(check.val()===data.payment){
                                    $(this).prop("checked",true);
                                    if(data.payment==="Paid By Cheque"){
                                        $(".div-5 .div-5_radio-1 input[name='exampleRadios1']").prop("checked",true);
                                    }else if(data.payment==="Successful Online Transfer"){
                                        $(".div-5 .div-5_radio-2 input[name='exampleRadios1']").prop("checked",true);
                                    }else if(data.payment==="Paid in Cash"){
                                        $(".div-5 .div-5_radio-3 input[name='exampleRadios1']").prop("checked",true);
                                    }else{
                                        $(".div-5 .div-5_radio-4 input[name='exampleRadios1']").prop("checked",true);
                                    }
                                 }
                             });

                             $("#exampleModal").modal();
                        }

                    });


            });

            $('.mybadge_delete').click(function(event){
                event.preventDefault();
                var id=$(this).attr("href").split("=");
                $('.invoice_id').text(id[1]);
                $('.delete_id').val(id[1]);
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
                                     url:'/invoicerest/delete_invoice/'+data,
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


        });