package com.example.demo.controllers;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categories")
@Api(tags = "Category Controller", description = "Operaciones relacionadas con categorías")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @GetMapping
    @ApiOperation(value = "Listar todas las categorías", notes = "Retorna una lista de todas las categorías disponibles")
    @ApiResponse(code = 200, message = "Lista de categorías recuperada exitosamente")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una categoría por ID", notes = "Retorna una categoría según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Categoría encontrada exitosamente"),
        @ApiResponse(code = 404, message = "Categoría no encontrada")
    })
    public ResponseEntity<Category> getCategoryById(
            @ApiParam(value = "ID de la categoría", required = true, example = "1")
            @PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ApiOperation(value = "Crear una nueva categoría", notes = "Crea una nueva categoría con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Categoría creada exitosamente"),
        @ApiResponse(code = 400, message = "Datos de la categoría inválidos")
    })
    public ResponseEntity<Category> createCategory(
            @ApiParam(value = "Datos de la categoría", required = true)
            @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar una categoría existente", notes = "Actualiza una categoría según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Categoría actualizada exitosamente"),
        @ApiResponse(code = 404, message = "Categoría no encontrada"),
        @ApiResponse(code = 400, message = "Datos de la categoría inválidos")
    })
    public ResponseEntity<Category> updateCategory(
            @ApiParam(value = "ID de la categoría", required = true, example = "1")
            @PathVariable Long id,
            @ApiParam(value = "Datos actualizados de la categoría", required = true)
            @RequestBody Category category) {
        return categoryService.update(id, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar una categoría", notes = "Elimina una categoría según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Categoría eliminada exitosamente"),
        @ApiResponse(code = 404, message = "Categoría no encontrada")
    })
    public ResponseEntity<Void> deleteCategory(
            @ApiParam(value = "ID de la categoría", required = true, example = "1")
            @PathVariable Long id) {
        if (categoryService.findById(id).isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


