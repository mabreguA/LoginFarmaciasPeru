Feature: Realizar el login para acceder a la pagina de inkafarma


  @LoginF
  Scenario Outline: Login y busqueda de producto
    Given Ingresamos a InkaFarma
    And Ingreso los datos como el Correo "<Correo>" y Contraseña "<Contrasena>"
    And Ingreso el producto "<Producto>"
    And Cierro sesion
    Examples:
      | Correo                     | Contrasena      | Producto |
      | marcelino.abregu@gmail.com | Automatizacion1 | Panadol  |
    #Aqui se puede cambiar el correo y pass, para el caso de inkafarma usar el cgarcia@farmaciasperuanas.pe