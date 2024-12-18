import re

class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'

class SurnameExtractor:
    def __init__(self):
        self.pattern = re.compile(r'\b([А-ЯЁ][а-яё]+(?:-[А-ЯЁ][а-яё]+){0,1})\s(?:[А-ЯЁ]\.\s?){1,2}\b', re.UNICODE)

    def extract_surnames(self, text):
        surnames = self.pattern.findall(text)
        return sorted(set(surnames))

if __name__ == "__main__":
    extractor = SurnameExtractor()

    test_texts = [
        "Студент Вася вспомнил, что на своей лекции Балакшин П.В. упоминал про старшекурсников, которые будут ему помогать: Анищенко А.А., Машина Е.А. и Голованова-Иванова Д.В.",
        "На конференции выступали Иванов И.И., Петров П.П., Сидоров С.С. и Смирнов-Сидоров С.С.",
        "Доклад подготовили Жуков А.М., Петрова А.С., Лермонтов М.Ю. и Толстой Л.Н.",
        "В соревнованиях участвовали спортсмены: Капустин А.А., Зеленский В.В., Лисицын-Кошкин П.П.",
        "Преподаватели университета: Сергеев С.С., Кузнецов-Кузьмин С.С., Петров С.С.",
    ]

    manual_results = [
        ['Анищенко', 'Балакшин', 'Голованова-Иванова', 'Машина'],
        ['Иванов', 'Петров', 'Сидоров', 'Смирнов-Сидоров'],
        ['Жуков', 'Лермонтов', 'Петрова', 'Толстой'],
        ['Зеленский', 'Капустин', 'Лисицын-Кошкин'],
        ['Кузнецов-Кузьмин', 'Петров', 'Сергеев'],
    ]

    for i, text in enumerate(test_texts):
        print(f"{bcolors.HEADER}Тест {i+1}:{bcolors.ENDC} {text}")
        manual_surnames = sorted(manual_results[i])
        extracted_surnames = extractor.extract_surnames(text)
        print(f"{bcolors.OKBLUE}Ожидаемый результат: {', '.join(manual_surnames)}{bcolors.ENDC}")
        print(f"{bcolors.OKCYAN}Результат программы: {', '.join(extracted_surnames)}{bcolors.ENDC}")
        if manual_surnames == extracted_surnames:
            print(f"{bcolors.OKGREEN}Результаты совпадают.{bcolors.ENDC}")
        else:
            print(f"{bcolors.FAIL}Результаты не совпадают.{bcolors.ENDC}")
        print("-" * 50)

    user_text = input("Введите текст для извлечения фамилий: ")
    extracted_surnames = extractor.extract_surnames(user_text)
    if extracted_surnames:
        print(f"{bcolors.OKGREEN}Найденные фамилии: {', '.join(extracted_surnames)}{bcolors.ENDC}")
    else:
        print(f"{bcolors.WARNING}В тексте не найдено ни одной фамилии по заданному шаблону.{bcolors.ENDC}")
