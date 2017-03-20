grammar Tal_OURS;

SELECT:'vouloir';

ET:'et';
OU:'ou';

MOTS:'contenir';

RUBRIQUES:'rubrique';

DATES:'date';
MOIS:'0'(('0'('1'..'9'))|'10'|'11'|'12');
ANNEE:('1'|'2')'0'..'9''0'..'9''0'..'9';
JOUR:('0'..'9')|(('1'|'2')'0'..'9')|'30'|'31';

WS:(' ' |'\t' | '\r' | 'je' | 'qui' | 'dont') {skip();} | '\n';

VAR:('A'..'Z' | 'a'..'z'|'\u00a0'..'\u00ff')(('a'..'z')|('0'..'9')|'-'|('\u00a0'..'\u00ff'))+;


listerequetes returns [String sql = ""] @init {Arbre lr_arbre;}: 
	r = requete {
		lr_arbre = $r.req_arbre;
		sql = lr_arbre.sortArbre();
	}
;

requete returns [Arbre req_arbre = new Arbre("")] @init {Arbre ps_arbre;} : 
	(SELECT+) {
		req_arbre.ajouteFils(new Arbre("","select distinct"));
	} 
	cols = columns {
		ps_arbre = $cols.columns_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","from titretext te, date d where d.fichier = te.fichier and "));
	}
	(MOTS mots = contenir {
		req_arbre.ajouteFils(new Arbre("","!("));
		ps_arbre = $mots.les_pars_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})?
	(
	(
	(RUBRIQUES rubs = rubriques {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $rubs.rubriques_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})
	(MOTS mots = contenir {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $mots.les_pars_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})?
	(DATES dats = dates {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $dats.dates_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	}
	(MOTS mots = contenir {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $mots.les_pars_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})?
	)?
	)
	|
	(
	(DATES dats = dates {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $dats.dates_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})
	(MOTS mots = contenir {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $mots.les_pars_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})?
	(RUBRIQUES rubs = rubriques {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $rubs.rubriques_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	}
	(MOTS mots = contenir {
		req_arbre.ajouteFils(new Arbre("","and !("));
		ps_arbre = $mots.les_pars_arbre;
		req_arbre.ajouteFils(ps_arbre);
		req_arbre.ajouteFils(new Arbre("","!)"));
	})?
	)?
	)
	)
;

columns returns [Arbre columns_arbre = new Arbre("")] @init {Arbre col1_arbre, col2_arbre;} : 
	col1 = column {
		col1_arbre = $col1.column;
		columns_arbre.ajouteFils(col1_arbre);
	}
	(c = (ET) {
		columns_arbre.ajouteFils(new Arbre("", ","));
	}		
	col2 = column {
		col2_arbre = $col2.column;
		columns_arbre.ajouteFils(col2_arbre);
	})*

;

column returns [Arbre column = new Arbre("")] :
	c = (VAR) {
		String s = null;
		switch(c.getText()) {
		case "fichier":
			s = "te.fichier as fichier";
			break;
		case "rubrique":
			s = "te.rubrique as rubrique";
			break;
		case "numero":
			s = "te.numero as numero";
			break;
		default:
			s = c.getText();
		}
		column.ajouteFils(new Arbre("", s));
	}	
;

rubriques returns [Arbre rubriques_arbre = new Arbre("")] @init {Arbre rub1_arbre, rub2_arbre;} : 
	rub1 = rubrique {
		rub1_arbre = $rub1.rubrique;
		rubriques_arbre.ajouteFils(rub1_arbre);
	}
	(c = (OU) {
		rubriques_arbre.ajouteFils(new Arbre("", "OR"));
	}		
	rub2 = rubrique {
		rub2_arbre = $rub2.rubrique;
		rubriques_arbre.ajouteFils(rub2_arbre);
	})*

;

rubrique returns [Arbre rubrique = new Arbre("")] :
	c = VAR {
		rubrique.ajouteFils(new Arbre("rubrique = ", "'"+c.getText()+"'"));
	}	
;

dates returns [Arbre dates_arbre = new Arbre("")] @init {Arbre date1_arbre, date2_arbre;} : 
	dat1 = date {
		date1_arbre = $dat1.date;
		dates_arbre.ajouteFils(date1_arbre);
	}
	(c = (OU) {
		dates_arbre.ajouteFils(new Arbre("", "OR"));
	}	
	DATES?	
	dat2 = date {
		date2_arbre = $dat2.date;
		dates_arbre.ajouteFils(date2_arbre);
	})*
;

date returns [Arbre date = new Arbre("")]:
		                 (
			(
			  jour = JOUR
				{
					date.ajouteFils(new Arbre("jour =", "'"+jour.getText()+"'"));
				}
			)
			(
			  mois = MOIS
				{
					date.ajouteFils(new Arbre("and mois =", "'"+mois.getText().replaceAll("^0(\\d{2})+","$1")+"'"));
				}
			)?
			(
			  annee = ANNEE
				{
					date.ajouteFils(new Arbre("and annee =", "'"+annee.getText()+"'"));
				}
			)?
		                  )
		            |
		                  (
			(
			  mois = MOIS
				{
					date.ajouteFils(new Arbre("mois =", "'"+mois.getText().replaceAll("^0(\\d{2})+","$1")+"'"));
				}
			)
			(
			  annee = ANNEE
				{
					date.ajouteFils(new Arbre("and annee =", "'"+annee.getText()+"'"));
				}
			)?
		                  )
		            |
			(
			  annee = ANNEE
				{
					date.ajouteFils(new Arbre("annee =", "'"+annee.getText()+"'"));
				}
			)
;

contenir returns [Arbre les_pars_arbre = new Arbre("")]
	@init	{Arbre par1_arbre, par2_arbre;} : 
		par1 = mot 
			{
				par1_arbre = $par1.lepar_arbre;
				les_pars_arbre.ajouteFils(par1_arbre);
			}
		((c = (OU)
			{
				les_pars_arbre.ajouteFils(new Arbre("", "OR"));
			}		
		par2 = mot
			{
				par2_arbre = $par2.lepar_arbre;
				les_pars_arbre.ajouteFils(par2_arbre);
			}
		)
		|
		(c = (ET)
			{
				les_pars_arbre.ajouteFils(new Arbre("", "!(!( INTERSECT "));
			}		
		par3 = VAR
			{
				les_pars_arbre.ajouteFils(new Arbre("", "'"+par3.getText()+"'"));
				les_pars_arbre.ajouteFils(new Arbre("", " !)!)"));
			}
		)
		)*

;

mot returns [Arbre lepar_arbre = new Arbre("")] :
	 (v = VAR
		{
			lepar_arbre.ajouteFils(new Arbre("mot =", "'"+v.getText()+"'"));
		}
	 )
;

