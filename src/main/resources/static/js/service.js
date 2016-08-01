angular.module("app").factory("Servicos", function($q, $http){
	return {
		tarefas: function() {
			return $http.get("/tarefas/lista");
		},
		totais: function() {
			return $http.get("/tarefas/totais");
		},
		finalizar: function(codigo) {
			return $http.put("/tarefas/" + codigo + "/finalizar");
		},
		iniciar: function(codigo) {
			return $http.put("/tarefas/" + codigo + "/iniciar");
		},
		usuarios: function() {
			return $http.get("/usuarios/lista");
		},
	};
});