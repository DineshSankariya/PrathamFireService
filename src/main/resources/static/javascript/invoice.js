  $(document).ready(function(){
            var glob='false';
            $('[data-toggle="tooltip"]').tooltip();
            $('.container_line-2_row-limit').change(function(){

                var limit=$(this).val();

                $('.container_line-2_row-limit:selected').removeAttr("selected");
                $(this).attr("selected");
                window.location.href="?page="+0+"&size="+limit;
                //alert(limit);

            })

            //data search event in table
            $('.search_input').on('keyup keydown',function(){
                //console.log("key released event is triggered")
                search_data($(this).val());

            });
            //table action event
            $('.add').click(function(event){

                event.preventDefault();
                //$('#exampleModal').modal();

                //alert($(this).attr("href"));
             var date=$("input[name='date']").val("");
            var bank=$("input[name='exampleRadios']:checked").prop("checked",false);
            var payment=$(".div-5 input[name='exampleRadios1']:checked").prop("checked",false);
            var client=$('.div-3_select').val("");
            var item=$('.div-4_item').val("");
            var code=$('.code').val("");
            var capacity=$('.capacity').val("");
            var rate=$('.rate').val("");
            var quantity=$('.nos').val("");
            var price=$('.amount').val("");
            $('.mycheckbox').prop("checked",false);
            $("#exampleModal").modal();
            });

            $('.save').click(function(){

                 var date=$("input[name='date']").val();
                var bank=$("input[name='exampleRadios']").val();
                var payment=$(".div-5 input[name='exampleRadios1']").val();
                var client=$('.div-3_select').val();
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
                    data={
                        "bank":bank,
                        "client":client,
                        "item":item,
                        "code":code,
                        "capacity":parseInt(capacity),
                        "rate":parseFloat(rate),
                        "nos":parseInt(quantity),
                        "amount":parseFloat(price),
                        "date":date,
                        "payment":payment

                    };
                    datatopass={"invoice":data};
                    //console.log(datatopass);
                    $.ajax({
                        contentType: 'application/json;charset=UTF-8',
                        url:'/invoicerest/saveinvoice',
                        type: 'POST',
                        data: JSON.stringify(data),
                        dataType: 'json',
                        success:function(data){
                            //console.log("hello");
                            if(data.success=="ok"){
                                console.log(data);
                                var alert_success="<div id='success_save'> Saved successfully </p>";
                                $('.add_invoice_header .modal-title').append(alert_success);
                                 $('#success_save').css({"width":"500px","margin-left":"10px","font-size":"15px","display":"inline-block"});
                                 $('#success_save').addClass("alert alert-success");
                                //$('#exampleModal').modal('toggle');
                                setTimeout(function(){
                                    //$('#exampleModal').modal("toggle");



                                    window.location.reload();
                                },1500);
                            }

                        },
                        error:function(){
                            console.log("hello");
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
                            //console.log(data);
                            $(".div-5 input[name=\"exampleRadios1\"]:checked").prop('checked',false);
                            $(".div-1 input[name=\"exampleRadios\"]:checked").prop('checked',false);
                            $("input[name='date']").val(data.date);
                            $(".div-1 input[name='exampleRadios']").val(data.bank);
                            $('.div-3_select').val(data.client);
                            $('.div-4_item').val(data.item);
                            $('.code').val(data.code);
                            $('.capacity').val(data.capacity);
                            $('.rate').val(data.rate);
                            $('.nos').val(data.nos);
                            $('.amount').val(data.amount);
                             $('.mycheckbox').prop("checked",true);
                            //$(".div-5 input[name=\"exampleRadios\"][value=\" + data.payment + \"]").prop('checked', true);
                             //console.log( $(".div-1 input[name='exampleRadios']").val());
                             //console.log( $(".div-5 input[name='exampleRadios']"));
                             var banks=$("input[name='exampleRadios']");
                             $(".div-1 input[name='exampleRadios']").each(function(){
                                var check=$(this);
                                 //console.log( check.val());
                                 if(check.val()===data.bank){
                                    if(data.bank==="BOB"){
                                        //console.log( check.val());
                                        $(".div-1 .div-1_radio-1 input[name='exampleRadios']").prop("checked",true);
                                    }else{
                                        $(".div-1 .div-1_radio-2 input[name='exampleRadios']").prop("checked",true);
                                    }


                                 }
                             });

                              $(".div-5 input[name='exampleRadios1']").each(function(){
                                 var check=$(this);
                                  //console.log( check.val());
                                 if(check.val()===data.payment){
                                    $(this).prop("checked",true);
                                    //console.log( check.value);
                                    if(data.payment==="Paid By Cheque"){
                                       // console.log( check.val());
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
                console.log(id);
                $('.invoice_id').text(id[1]);
                var id_todelete=parseInt($('.delete_id').val(id[1]));
                $("#delete_invoice").modal();

            });



             $('.delete_btn').click(function(){
                event.preventDefault();
                var id=$('.delete_id').val();
                console.log(id);
                console.log($(this));

               // var id_todelete=parseInt(id[1]);
                //console.log(id_todelete);
                del(id);

             })

              function del(data){
                             console.log(data);
                             $.ajax({

                                     contentType: 'application/json;charset=UTF-8',
                                     url:'/invoicerest/delete_invoice/'+data,
                                     success:function(data){
                                         //console.log(data);
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

                        // $('tbody tr:first td').replaceWith("<i>No result found</i>");
                       // $(this).innerHTML("<i>No result found</i>");
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
                    }/*else{
                        var datas=$('.mytable tbody tr').length;
                        console.log(num);

                        $('.container_invoice_table').prepend("<div class=\"alert alert-primary mydiv-1\" >"+num+"No data found </div>");
                    }*/
                }

                if(!data){
                     $('.container_invoice_table .mydiv').remove();
                     $('.container_invoice_table .mydiv-1').remove();
                }

            }

            /*if(glob=='true'){
                    if($('.container_invoice_table .mydiv-1').length){

                    }
                    else{
                        var datas=$('.mytable tbody tr').length;
                        console.log(datas);
                        $('.container_invoice_table').prepend("<div class=\"alert alert-primary mydiv-1\" >"+datas+"No data found </div>");
                    }
            }*/

        });