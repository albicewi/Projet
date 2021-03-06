/*

Auteurs : Laurent BRACQUART <lbracquart@atalan.fr>, Sébastien DELORME <sdelorme@atalan.fr>
URL : http://atalan.fr/
Date de création : 05 Juillet 2011
Datet de mise à jour : 08 mars 2013
Version : 1.1

Index :

    0/ Masquage des panneaux par défaut
    1/ Initialisation des liens d'accès aux panneaux
	2/ Gestion de l'affichage des panneaux

    Annexes/ Fonctions annexes

*/

$(document).ready(function()
{
	// 0/ Masquage des panneaux par défaut
	$panneaux = $('div.panneau').hide();
	
	// ---------------------------------------------------------------------------------------- //
	
	// 1/ Initialisation des liens d'accès aux panneaux
	
	$('h2.titre').each(function(i)
	{
		$this = $(this);
		ancre = $this.next($panneaux)[0].id;
		
		lien = $('<a>',
		{
			'href':				'#' + ancre,
			'aria-expanded':	'false',
			'aria-controls':	ancre
		});
		
		$this.wrapInner(lien);
	});
	
	// ---------------------------------------------------------------------------------------- //

    // 2/ Gestion de l'affichage des panneaux
	$('h2.titre > a').click(function() 
	{
		if ($(this).attr('aria-expanded') == 'false') 
		{
            $(this).attr('aria-expanded', true).parent().next($panneaux).show();
		} 
		else 
		{
            $(this).attr('aria-expanded', false).parent().next($panneaux).hide();
		}
		return false;
	}); 
});

// Annexes/ Fonctions annexes
