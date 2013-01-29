'use strict';

function TodoCtrl($scope, $http) {
  var todos = [];
  $http.get('/Todo/todo.json').success(function(data) {
    todos = $scope.todos = data;
  });
  
  $scope.newTodo = '';
  $scope.editedTodo = null;
  
  $scope.addTodo = function() {
    if ( !$scope.newTodo.length ) {
      return;
    }
    
    var todo = {
      id: null,
      description: $scope.newTodo,
      done: false
    };
    
    $http.post('todo', todo).success(function(id) {
      todo.id = id;
    });

    todos.push(todo);

    $scope.newTodo = '';
  };
  
  $scope.editTodo = function(todo) {
    $scope.editedTodo = todo;
  };

  $scope.doneEditing = function(todo) {
    $scope.editedTodo = null;
    if (!todo.description) {
      $scope.removeTodo(todo);
    }
    else {
      $http.put('todo', todo);
    }
  };

  $scope.removeTodo = function(todo) {
    $http.delete('todo/' + todo.id).success(function() {
      todos.splice(todos.indexOf(todo), 1);
    });
  };
  
}
TodoCtrl.$inject = ['$scope', '$http'];