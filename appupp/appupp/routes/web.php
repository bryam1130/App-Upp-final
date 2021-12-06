<?php

use Illuminate\Support\Facades\Route;

/*
| Web Routes
*/

Route::get('/', function () {
    return view('auth.login');
});


Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');

Route::resource('direcciones', App\Http\Controllers\DireccioneController::class)->middleware('auth');

Route::resource('empleados', App\Http\Controllers\EmpleadoController::class)->middleware('auth');