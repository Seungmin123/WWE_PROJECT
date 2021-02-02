let isChk = $('#wholeChk').data('chk');

function wohleCheck(){
   if($('#wholeChk').data('chk')==false){
           $('input[name=_selected_').prop('checked',true);
            $('#wholeChk').data('chk',true);
       }else{
           $('input[name=_selected_').prop('checked',false);
            $('#wholeChk').data('chk',false);
       }
   }