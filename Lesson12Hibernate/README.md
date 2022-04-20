# JDTask Lesson12
Создать три класса
1) Task - имеет следующие поля
- id
- name
- description

2) HomeTask exstends Task - имеет дополнительные поля
- startDate
- endDate
  @Embedded
- Исполнитель person (person имеет два поля имя и фамилия сделать Embeddable)
  @Embedded
- Смотрящий person (person имеет два поля имя и фамилия сделать Embeddable)

3) WorkTask exstends Task - имеет дополнительные поля
- cost

В трех ветках git реализовать 3 стратегии наследования (16 перзентация)
1 - Table per class hierarchy
2 - Table per subclass
3 - Table Per Concrete class mapping

Записать туда какие нить данные из main
Проверить через тесты на H2, подключить checkstyle, jacoco