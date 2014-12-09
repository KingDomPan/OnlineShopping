$().ready(function(){
  function hasPlaceholderSupport(){
    var element=document.createElement("input");
    return "placeholder" in element;
  }
  if(!hasPlaceholderSupport()){
      $("#create_accout").placeholder();
 
      $("input[autofocus=true]").focus();
  }
});