class cardCollection extends drawableZone{
	
	
	constructor(div,cards,expandInto,drawAsBacks,drawStates = true){
		super();
		this.div = div;
		this.cards = cards;
		this.changed = true;
		this.prepareForExpand();
		this.expandInto = expandInto;
		this.zone = div.attr("id");
        this.count = 0;
        this.drawAsBacks = drawAsBacks;
        this.drawStates = drawStates;
	}
	
	prepareForExpand(){
		let $this = this;
		this.div.children(".expandButton").click(function(){
			if(!expandedInUse){
				expandedInUse = true;
				$this.expand();
			}
		});
	}
	
	setZones(){
		for(let card of this.cards){
			card.setZone(this.zone);
		}
	}
	
	expand(){
		let $this = this;
		$this.expandInto.toggle();
		$this.forceDrawInDiv($this.expandInto);
		$this.expandInto.click(function(){
			$this.expandInto.empty();
			expandedInUse = false;
			$this.expandInto.hide();
			$('div.qtip:visible').qtip('hide');
            $this.forceRedraw();
		});
	}
	
	setCards(cards){
		this.cards = cards;
		this.changed = true;
	}
	
	setCardsFromCache(cardIDs, cache){
		let cards = [];
		for(let id of cardIDs){
			cards.push(cache.getByIID(id));
		}
        if(!this.drawStates){
            for(let card of cards){
                card.setShouldState(false);
            }
        }
        
		this.cards = cards;
        this.changed = true;
	}
	
	forceRedraw(){
		this.changed = true;
		this.draw();
	}
	
	pushCard(card){
		this.cards.push(card);
		this.changed = true;
	}
	
	fillDiv(div){
		let curDiv = div;
		let $this = this;
		if($this.cards.length < 1){
			return;
		}
		else{
		}
		let baseWidth = (curDiv.height() * WIDTH_RATIO) + 12;
		let rows = 1;
		while(baseWidth * ($this.cards.length / rows) >= curDiv.width() * .95){
			rows++;
			baseWidth = ((curDiv.height() / rows) * WIDTH_RATIO) + 12;
		}
		let maxInRow = Math.ceil($this.cards.length / rows);
		if(maxInRow == 0){
			console.log("Problem with row sizing in filldiv");
		}
		let total = 0;
		for(let x = 1; x <= rows; x++){
			curDiv.append('<div class="cardRow"></div');
			let curChild = curDiv.children().last();
			curChild.css('height', (curDiv.height() / rows) + "px");
			while(total < x * maxInRow && total < this.cards.length){
				//space to mekkit mejor
				curChild.append('<div class="cardBox"></div>');
				let cur = curChild.children().last();
				cur.css('height', (curDiv.height() / rows) + "px");
				$this.cards[total].setDiv(cur);
                $this.cards[total].setBacks($this.drawAsBacks);
				$this.cards[total].drawGivenSpace(cur);
				total++;
			}
		}
	}
	
	draw(){
		this.drawInDiv(this.div);
        this.changed = false;
        
	}
	
	forceDrawInDiv(div){
		this.changed = true;
		this.drawInDiv(div);
	}
	
	drawInDiv(div){
		if(this.changed){
			this.changed = false;
			div.empty();
			this.fillDiv(div);
            if(tooltipDisplay){
                this.prepareToolTips(div);
            }
            this.prepareHover();
			this.sizeCards(div);
            if(!isReplayMode){
                this.prepareDraggables();
                this.prepareDroppables();
                this.prepareDroppableZone();
            }
		}
	}
	
	prepareDraggables(){
        // let $this = this;
		// this.div.find(".card").draggable({ 
			// revert: false, 
			// helper: function(){
				// return "<div class='targetCursor'></div>";
			// },
			// cursorAt: { bottom: $this.getX(), left: $this.getY()}
		// });
        this.prepareDraggablesManual();
	}
    
    
    
    prepareDraggablesManual(){
        let $this = this;
                
        this.div.mouseup(function(event){
            mouseSystem.mouseupDiv($this,event);
        });
        
        this.div.find(".card").mousedown(function(event){
            mouseSystem.mousedown($(this).attr("id"),event)
        });
        
        this.div.find(".card").mouseup(function(event){
            mouseSystem.mouseupCard($(this).attr("id"),event);
        });
        $(document).mouseup(function(event){
            mouseSystem.mouseup(event);
        })
    }
    
    
    getX(){
        this.count+=100;
        return this.count;
    }
	
    getY(){
       this.count+=100;
       return this.count;
    }
    
	prepareDroppables(){
		// this.div.find(".card").droppable({
			// drop: function( event, ui ) {
				// $( this )
				  // .addClass( "cardSelected" );
				  // server.cardTargeted(ui.draggable.attr("id"),$(this).attr("id"));
			// },
			// greedy:true
		// })
	}
	
	prepareDroppableZone(){
		// this.div.droppable({
			// drop: function( event, ui ) {
				  // server.cardPlayed(ui.draggable.attr("id"),$(this));
			// }
		// });
	}
	
	sizeCards(div){
        div.children().children('.cardBox').css({
			'height':function(index, value){
                let height = $(this).height();
                if(height > 202){
                    return 190;
                }
                else{
                    return height - 12;
                }
			}
		});
		div.children().children('.cardBox').css({
			'width':function(index, value){
				return $(this).height() * WIDTH_RATIO;
			}
		});
	}

	prepareHover(){
        this.div.find(".cardBox").hover(function(){
            $('div.qtip:visible').qtip('hide');
          $(this).addClass("cardBoxHighlighted");  
        },
        function(){
            $(this).removeClass("cardBoxHighlighted");
        })
    }
    
    
    
	prepareToolTips(div){
		let maxHeight = $(document).height();
		let maxWidth = $(document).width();
		let height;
		if(maxHeight > 400){
			height = 350;
		}
		else{
			height = maxHeight * .8;
			$(".bigCardBox").css({
				fontSize : "10px",
				lineHeight : "90%"
			});
		}
		let width = height * WIDTH_RATIO;
		while(width > maxWidth){
			height -= 10;
			width = height * WIDTH_RATIO;
		}
		div.find('.hasTooltip').each(function() {
			$(this).qtip({
				content: {
					text: $(this).next('div') 
				},
				style: {
					height: height,
					width: width,
					classes: 'qtip-bootstrap no-mouse',
				},
				position: { 
					viewport: $(window),
					adjust: {
						method: 'flipinvert shift'
					},
                    target: 'mouse',
				}
			});
		});
	}
}