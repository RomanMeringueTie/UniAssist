# Экран занятия / задания
### Отображается информация о занятиии и задании
### Есть кнопка для отправки ответа на задания
## Методы API:
### При переходе на экран: GET student_classById (параметры - id)
- ### Успех: Получаем занятие (class).
- ### class:
    - #### subject
    - #### start_time
    - #### end_time
    - #### type
    - #### teacher
    - #### classroom
    - #### task
        - #### id
        - #### header
        - #### body
        - #### responses []:
            - #### id
            - #### student_id
            - #### body
            - #### mark
### При нажатии кнопки отправить ответ: POST student_responseById (параметры - id, body)
- ### Успех: Сообщение об успехе