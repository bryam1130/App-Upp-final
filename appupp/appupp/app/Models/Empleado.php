<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Empleado
 *
 * @property $id
 * @property $nombre
 * @property $apellidos
 * @property $edad
 * @property $genero
 * @property $organizacion
 * @property $cargo
 * @property $sueldo
 * @property $telefono
 * @property $direccion
 * @property $email
 * @property $contraseña
 * @property $created_at
 * @property $updated_at
 *
 * @package App
 * @mixin \Illuminate\Database\Eloquent\Builder
 */
class Empleado extends Model
{
    
    static $rules = [
		'nombre' => 'required',
		'apellidos' => 'required',
		'edad' => 'required',
		'genero' => 'required',
		'organizacion' => 'required',
		'cargo' => 'required',
		'sueldo' => 'required',
		'telefono' => 'required',
		'direccion' => 'required',
		'email' => 'required',
		'contraseña' => 'required',
    ];

    protected $perPage = 20;

    /**
     * Attributes that should be mass-assignable.
     *
     * @var array
     */
    protected $fillable = ['nombre','apellidos','edad','genero','organizacion','cargo','sueldo','telefono','direccion','email','contraseña'];



}
