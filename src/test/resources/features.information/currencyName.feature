Feature: Nombre de la moneda
    Como Usuario de un banco
    necesito saber el nombre de la moneda segun sus siglas
    para poder realizar cambios de monedas en diferentes paises

    Background:
        Given que el usuario de la  app ah le interesa saber el nombre de una sigla
    Scenario: Nombre de moneda correcta
        When el usuario elige la opcion de obtener nombre he ingresa la sigla de una moneda "USD"
        Then el ususario debería obtener de correctamente  "Dollars"
    Scenario: Nombre de moneda incorrecta
        When el usuario elige la opcion de obtener nombre he ingresa algun dato herroneo "gatos"
        Then el ususario debería obtener como respuesta  "Currency code NOT found"