<#assign content>
<#include "message_modal.ftl">
<div class="container">
  
  <div class="row">
    <div class="center-block menuDisplay">
    	<br>
        <h3 id="greeting"></h3> 
        <br>
	    <a href="/decks" id="decks" class="btn btn-default btn-lg btn-block shadowedButton">Decks</a>
	    <br><br>
        <a href="/replays" id="games" class="btn btn-default btn-lg btn-block shadowedButton">Replays</a>
	    <br><br>
        <a href="/lobbies" id="lobbies" class="btn btn-default btn-lg btn-block shadowedButton">Lobbies</a>
        <br><br>
    </div>
  </div>
</div>

<div id="startmodal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="startheader"></h4>
      </div>
      <div class="modal-body">
        <p id="startmessage"> </p>
      </div>
      <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">Start Tutorial</button>
        <button type="button" id="skip_tutorial" class="btn btn-default" data-dismiss="modal">Skip Tutorial</button>
      </div>
    </div>
  </div>
</div> 
${modal}
</#assign>
