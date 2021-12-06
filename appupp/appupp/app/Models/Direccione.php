<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Direccione
 *
 * @property $id
 * @property $direccion
 * @property $coordenadas
 * @property $nombre
 * @property $idusuario
 * @property $created_at
 * @property $updated_at
 *
 * @package App
 * @mixin \Illuminate\Database\Eloquent\Builder
 */
class Direccione extends Model
{
    
    static $rules = [
		'direccion' => 'required',
		'coordenadas' => 'required',
		'nombre' => 'required',
		'idusuario' => 'required',
    ];

    protected $perPage = 20;

    /**
     * Attributes that should be mass-assignable.
     *
     * @var array
     */
    protected $fillable = ['direccion','coordenadas','nombre','idusuario'];



}
