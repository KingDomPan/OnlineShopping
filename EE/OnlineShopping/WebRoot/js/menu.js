   function sc5(){
      if(document.documentElement.clientWidth>1000)
       document.getElementById("point").style.left=(document.body.clientWidth/2-document.getElementById("point").offsetWidth)+430+"px";
    }
    function scall(){
      sc5();
    }
function displaySubMenu(li) {
var subMenu = li.getElementsByTagName("ul")[0];
subMenu.style.display = "block"; 

var $li=$(li);
	$li.siblings().css('visibility', 'hidden');
} 
function hideSubMenu(li) { 
var subMenu = li.getElementsByTagName("ul")[0]; 
subMenu.style.display = "none"; 

var $li=$(li);
$li.siblings().css('visibility', 'visible');
} 