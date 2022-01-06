

window.onload = function(){
  document.getElementById("myDIV").style.display='none';
};

function showName() {
    alert("Here's the name: " );
}


function myFunction() {
  var x = document.getElementById("myDIV");
  if (x.style.display === "none") {
    x.style.display = "block";
	

  } else {
    x.style.display = "none";
  }
}

