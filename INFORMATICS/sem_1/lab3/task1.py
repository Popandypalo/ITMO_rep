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
    UNDERLINE = '\033[4m'

class SmileyCounter:
    def __init__(self, smiley=":-/"):
        self.smiley = smiley
        self.pattern = re.escape(smiley)

    def check_input(self, text):
        if not text.strip():
            raise ValueError("Ошибка ввода. Кажется, вы ничего не ввели :-/.")

    def count_smileys(self, text):
        try:
            self.check_input(text)
            count = len(re.findall(self.pattern, text))
            return count
        except Exception as e:
            return e

    def get_result_message(self, count):
        if isinstance(count, Exception):
            return str(count)
        elif count == 0:
            return f"{bcolors.WARNING}Кажется, в вашем тексте нет ни одного смайлика :-/.{bcolors.ENDC}"
        else:
            if 10 <= count % 100 <= 20:
                word_ending = "ов"
            else:
                endings = {1: "", 2: "а", 3: "а", 4: "а"}
                word_ending = endings.get(count % 10, "ов")
            return f"{bcolors.OKGREEN}В вашем тексте найдено {count} смайлик{word_ending} :-/.{bcolors.ENDC}"

if __name__ == "__main__":
    counter = SmileyCounter(":-/")

    test_texts = [
        "Сегодня было очень смешно :-/, хотя погода не очень.",
        "Не понимаю, почему все смеются :-/ :-/ :-/.",
        "Это просто невероятно :-/! Просто не верится :-/ :-/.",
        "Никаких эмоций :-/ здесь нет.",
        "Просто текст без смайликов.",
        ":-/ :-/ :-/ :-/ :-/ :-/",
    ]
    manual_counts = [1, 3, 3, 1, 0, 6]

    for i, text in enumerate(test_texts):
        print(f"{bcolors.HEADER}Тест {i+1}:{bcolors.ENDC} {text}")
        manual_count = manual_counts[i]
        program_count = counter.count_smileys(text)
        result_message = counter.get_result_message(program_count)
        print(f"{bcolors.OKBLUE}Ожидаемый результат: {manual_count}{bcolors.ENDC}")
        print(f"{bcolors.OKCYAN}Результат программы: {program_count}{bcolors.ENDC}")
        print(f"Сообщение: {result_message}")
        match = "Да" if manual_count == program_count else "Нет"
        color = bcolors.OKGREEN if match == "Да" else bcolors.FAIL
        print(f"{color}Совпадает ли результат? {match}{bcolors.ENDC}")
        print("-" * 50)

    user_text = input("Введите текст для подсчета смайликов: ")
    program_count = counter.count_smileys(user_text)
    result_message = counter.get_result_message(program_count)
    print(result_message)
