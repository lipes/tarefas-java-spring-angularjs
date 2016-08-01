$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('id');
	var action = button.data('acao');
	console.log(action);
	var modal = $(this);
	var form = modal.find('form');
	
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
});