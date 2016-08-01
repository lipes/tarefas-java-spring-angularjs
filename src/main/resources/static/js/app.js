(function(){
	var app = angular.module("app", ["ngLocale"]);
	app.controller("Tarefas", ['$scope','Servicos', function($scope, Servicos){
		$scope.tarefas = [];
		$scope.totais = [];
		
		Servicos.tarefas().then(function(response){
			$scope.tarefas = response.data; 
		});
		
		function totais(){
			Servicos.totais().then(function(response){
				$scope.totais = response.data; 
			});
		}
		totais();
		
		$scope.finalizarTarefa = function(tarefa){
			Servicos.finalizar(tarefa.id).then(function(data){
				$(".status" + tarefa.id).html("<span class='label label-success'>FINALIZADA</span>")
				tarefa.pendente = false;
			});
			totais();
		}
		
		$scope.iniciarTarefa = function(tarefa){
			Servicos.iniciar(tarefa.id).then(function(data){
				$(".status" + tarefa.id).html("<span class='label label-info'>INICIADA</span>")
				tarefa.pendente = false;
				tarefa.iniciada = true;
			});
			totais();
		}
	}]);
	app.controller("Usuarios", ['$scope','Servicos', function($scope, Servicos){
		$scope.usuarios = [];
		Servicos.usuarios().then(function(response){
			$scope.usuarios = response.data; 
		});
	}]);
})();