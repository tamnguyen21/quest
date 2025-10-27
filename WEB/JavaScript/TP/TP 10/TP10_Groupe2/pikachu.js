//1) saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posX=0;
var posY=0;
var locX = 750;
var locY = 480;
var mouvement=30;
var pokemon="pikachu";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");

document.getElementById("inputName").onkeyup=verifInput;

document.getElementById("btnStart").onclick=lancer;

function verifInput(event)
{
  let nom=document.getElementById("inputName").value;
  if(nom=="")
  {
    btnStart.disabled=true;
  }
  else
  {
    if(event.keyCode==13 || event.key=="Enter")
    {
      imgPikachu.title=nom;
      lancer();
    }
    else{
      btnStart.disabled=false;
    }
  }
}


function lancer(){
  document.getElementById("ecranjeu").style.setProperty("display","flex");
  document.getElementById("formStart").style.setProperty("display","none");
  /*document.getElementById("imgBarquePika").style.setProperty("display","block");*/


  document.body.onkeydown=deplacement;
  let titre = document.getElementById("inputName").value;
  imgPikachu.title=titre;

}


function deplacement(event)
{
  if(event.key=="ArrowDown" || event.key=="s")
  {
    direction="Down";
    if(posY<660)
    {
      posY+=mouvement;
    }
  }
  else if(event.key=="ArrowRight" || event.key=="d")
  {
    direction="Right";
    if(posX<660)
    {
      posX+=mouvement;
    }
  }
  else if(event.key=="ArrowLeft" || event.key=="q" )
  {
    if(posX==0 && posY==300){
      if(confirm("Voulez-vous embarquer ?")){
        document.getElementById("pikachu").style.setProperty("display","none");
        document.getElementById("imgBarquePika").setAttribute("src","assets/img/BarqueUp.png");
        document.body.onkeydown=mouvBarque;
        return;
      }
    }
    direction="Left";
    if(posX >0)
    {
      posX-=mouvement;
    }
  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {
    direction="Up";
    if(posY >0)
    {
      posY-=mouvement;
    }
  }
  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");
  console.log(posX);
  console.log(posY);

}



function mouvBarque(event)
{
  var barq = document.getElementById("barquePikachu");

  console.log("ok");
  if(event.key=="ArrowDown" || event.key=="s")
  {
    document.getElementById("imgBarquePika").setAttribute("src","assets/img/baqueDown.png");
    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(0deg)")
    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(180deg)")


    direction="Down";
    if(locY!=(90)){
      locY+=mouvement;
    }
    else if((locY==(90)) && (locX<=(750) || locX>=(1500))) {
      locY+=mouvement;
    }
  }
  else if(event.key=="ArrowRight" || event.key=="d")
  {
    document.getElementById("imgBarquePika").setAttribute("src","assets/img/barqueLeft.png");
    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(0deg)")
    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(90deg)")
    direction="Right";
    if(locX!=750){
      locX+=mouvement;
    }
    else if((locX==750) && (locY<=(90) || locY>=(840))) {
      locX+=mouvement;
    }
  }


  else if(event.key=="ArrowLeft" || event.key=="q" )
  {
    document.getElementById("imgBarquePika").setAttribute("src","assets/img/barqueRight.png");
    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(0deg)")

    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(-90deg)")
    direction="Left";

    if(locX!=1500){
      locX-=mouvement;
    }
    else if((locX==1500) && (locY<=(90) || locY>=(840))) {
      locX-=mouvement;
    }
  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {
    document.getElementById("imgBarquePika").setAttribute("src","assets/img/barqueUp.png");
    document.getElementById("imgBarquePika").style.setProperty("transform","rotate(0deg)")

    direction="Up";

    if(locY!=(840)){
      locY-=mouvement;
    }
    else if((locY==(840)) && (locX<=(750) || locX>=(1500))) {
      locY-=mouvement;
    }
  }

  barquePikachu.style.top=locY+"px";
  barquePikachu.style.left=locX+"px";
  console.log(locX);
  console.log(locY);
}
