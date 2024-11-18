# Экран занятия / задания
### Отображается информация о занятиии и задании
### Есть кнопка для отправки ответа на задания
## Методы API:
### При переходе на экран: GET student_scheduleById (параметры - id, date, group и start_time)
- ### Успех: Получаем занятие (class).
- ### class:
    - #### subject
    - #### start_time
    - #### end_time
    - #### type
    - #### teacher
    - #### classroom
    - #### task
        - #### header
        - #### body
        - #### responses []:
            - #### id
            - #### student_id
            - #### body
            - #### mark
### При нажатии кнопки отправить ответ: POST student_responseById (параметры - id, student_id, body)
- ### Успех: Сообщение об успехе