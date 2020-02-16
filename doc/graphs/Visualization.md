# Візуалізація графів
Існує багато стратегій компонування графів:
* У ![системі силового компонування](https://en.wikipedia.org/wiki/Force-directed_graph_drawing), програми зображення графів змінюють початкове розміщення вершин шляхом безперервного переміщення вершин відповідно до системи сил, заснованої на фізичних метафорах, пов'язаних з системами пружин або молекулярної механіки. Зазвичай, ці системи поєднують в собі сили тяжіння між сусідніми вершинами з силами відштовхування між усіма парами вершин, щоб знайти макет, в якому довжини сторін малі в той час як вершини добре розділені. Ці системи можуть виконувати метод найшвидшого спуску на основі мінімізації з функції енергії, або ж вони можуть перевести свої сили безпосередньо в швидкості або прискорення для рухомих вершин.
* ![Метод спектрального макету](https://en.wikipedia.org/wiki/Spectral_layout) використовує власні вектори матриці такі як Лапласів отриманого з матриці суміжності графа.
* Ортогональні методи компонування, що дозволяють ребрам графа йти горизонтально або вертикально, паралельно осям координат макета. Ці методи були спочатку розроблені для схем надвеликого рівня інтеґрації, ДП і проблем компонування, але вони також були пристосовані до зображення графів.
* Алгоритми компонування дерев показують укорінене деревоподібнi утворення. Часто в техніці під назвою "компонування повітряних кульок" діти кожного вузла на дереві малюють по колу, що оточує вузол, при цьому радіуси цих кіл зменшуються на нижчих рівнях дерева, щоб ці кола не перетиналися.
* ![Методи багаторівневого зображення графу](https://en.wikipedia.org/wiki/Layered_graph_drawing) (часто звані зображення у стилі Сугияма) найкраще підходять для орієнтованого ациклічного графу або графів, близьких до ациклічних, таких як графи залежностей між модулями або функціями в програмній системі. У цих методах, вузли графа розташовані на горизонтальних шарах з використанням методів, таких як ![Алгоритм Коффмана-Грекхема](https://en.wikipedia.org/wiki/Coffman%E2%80%93Graham_algorithm), таким чином, що більшість ребер йдуть вниз від одного шару до іншого; після цього кроку, вузли в межах кожного шару виконані з метою мінімізації перетинів.
* ![Дугова діаграма](https://en.wikipedia.org/wiki/Arc_diagram), це стиль компонування, що веде свій шлях з 1960-х років, розташуємо вершини в одну лінію; сторони можуть бути зображені як півкола над і під лінією, або криві, що з'єднаних з декількох півкіл.
* ![У круговій схемі](https://en.wikipedia.org/wiki/Circular_layout) вершини обережно розташовані по колу, з метою зменшення перетинів та розташування сусідніх вершин якомога ближче одне до одного. Сторони можуть бути зображені як хорди кола чи його арки зсередини чи зовні. Іноді декілька кіл можуть бути використані.
* У ![макеті переважання](https://en.wikipedia.org/wiki/Dominance_drawing) вершини записуються таким чином, що кожна вершина вгорі, справа, або і так і так від іншої, якщо і тільки якщо вона досяжна з цієї вершини. Таким чином, стиль розташування робить відношення досяжності графа візуально очевидним.

## Силове компонування
![](https://upload.wikimedia.org/wikipedia/commons/9/9b/Social_Network_Analysis_Visualization.png)
## Спектральний макет
![Original](https://github.com/JuliaGraphs/NetworkLayout.jl)

![](https://cloud.githubusercontent.com/assets/8404278/17638718/a0b451ca-6109-11e6-9a66-fd22332b8541.png)
## Ортогональний макет
![Original](http://docs.yworks.com/yfiles/doc/developers-guide/orthogonal_layouter.html)

![](http://docs.yworks.com/yfiles/doc/developers-guide/figures/ortho_normal.jpg)
## Дерево
![Original](https://github.com/JuliaGraphs/Nethttps://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Goldner-Harary-linear.svg/1280px-Goldner-Harary-linear.svg.pngworkLayout.jl)

![](https://cloud.githubusercontent.com/assets/8404278/17638844/afd280a4-610a-11e6-8fea-5c99808bd740.png)

## Метод багаторівневого зображення
![](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/DC%2B%2B_derivatives.svg/1920px-DC%2B%2B_derivatives.svg.png)
## Дугова діаграма
![](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Goldner-Harary-linear.svg/1280px-Goldner-Harary-linear.svg.png)
## Кругова схема
![](https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Chvatal_Lombardi.svg/1024px-Chvatal_Lombardi.svg.png)
## Макет переважання
![](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Dominance_drawing.svg/1024px-Dominance_drawing.svg.png)
# Джерела
* Wikipedia contributors. (2020, February 6). Graph drawing. In Wikipedia, The Free Encyclopedia. Retrieved 17:31, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Graph_drawing&oldid=939481745
* Wikipedia contributors. (2019, December 18). Force-directed graph drawing. In Wikipedia, The Free Encyclopedia. Retrieved 17:32, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Force-directed_graph_drawing&oldid=931360730
* Wikipedia contributors. (2019, January 24). Spectral layout. In Wikipedia, The Free Encyclopedia. Retrieved 17:32, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Spectral_layout&oldid=879936336
* Wikipedia contributors. (2019, December 6). Layered graph drawing. In Wikipedia, The Free Encyclopedia. Retrieved 17:32, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Layered_graph_drawing&oldid=929466990
* Wikipedia contributors. (2019, December 22). Arc diagram. In Wikipedia, The Free Encyclopedia. Retrieved 17:32, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Arc_diagram&oldid=932012322
* Wikipedia contributors. (2018, February 6). Circular layout. In Wikipedia, The Free Encyclopedia. Retrieved 17:33, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Circular_layout&oldid=824222672
* Wikipedia contributors. (2017, June 22). Dominance drawing. In Wikipedia, The Free Encyclopedia. Retrieved 17:33, February 16, 2020, from https://en.wikipedia.org/w/index.php?title=Dominance_drawing&oldid=787016498
* NetworkLayout.jl. JuliaGraphs in GitHub https://github.com/JuliaGraphs/NetworkLayout.jl
