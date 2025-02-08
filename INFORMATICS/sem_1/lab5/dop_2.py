import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from datetime import datetime

class DataLoader:
    def __init__(self, file_path: str):
        self.file_path = file_path
        self.columns = ['Date', 'Open', 'High', 'Low', 'Close']

    def load_data(self) -> pd.DataFrame:
        try:
            return pd.read_csv(
                self.file_path,
                delimiter=';',
                header=None,
                names=self.columns,
                skiprows=1,
                parse_dates=['Date'],
                date_parser=lambda x: datetime.strptime(x, '%d.%m.%Y'),
                converters={
                    'Open': pd.to_numeric,
                    'High': pd.to_numeric,
                    'Low': pd.to_numeric,
                    'Close': pd.to_numeric
                }
            )
        except FileNotFoundError:
            raise FileNotFoundError(f"Файл {self.file_path} не найден")
        except Exception as e:
            raise RuntimeError(f"Ошибка загрузки данных: {str(e)}")

class DataProcessor:
    def __init__(self, data: pd.DataFrame):
        self.data = data

    def filter_by_dates(self, target_dates: list) -> pd.DataFrame:
        try:
            target_dates = [datetime.strptime(d, '%d.%m.%Y') for d in target_dates]
            return self.data[self.data['Date'].isin(target_dates)]
        except Exception as e:
            raise ValueError(f"Ошибка фильтрации данных: {str(e)}")

class BoxPlotVisualizer:
    def __init__(self, data: pd.DataFrame, target_dates: list):
        self.data = data
        self.target_dates = target_dates
        self.plot_columns = ['Open', 'High', 'Low', 'Close']

    def _prepare_plots(self) -> tuple:
        fig, axes = plt.subplots(1, len(self.target_dates), figsize=(15, 5))
        plt.subplots_adjust(wspace=0.5)
        return fig, axes

    def _plot_single_box(self, ax: plt.Axes, date: datetime) -> None:
        date_str = date.strftime('%d.%m.%Y')
        daily_data = self.data[self.data['Date'] == date]

        if not daily_data.empty:
            sns.boxplot(data=daily_data[self.plot_columns], ax=ax)
            ax.set_title(date_str)
        else:
            ax.set_title(f"{date_str} (Нет данных)")
            ax.set_xticks([])
            ax.set_yticks([])

    def visualize(self) -> None:
        fig, axes = self._prepare_plots()
        for idx, date in enumerate(self.target_dates):
            self._plot_single_box(axes[idx], date)
        plt.show()

def main():

    FILE_PATH = "ДопЗадание2.csv"
    TARGET_DATES = [
        '06.11.2018',
        '04.12.2018',
        '04.10.2018',
        '04.09.2018'
    ]

    try:
        loader = DataLoader(FILE_PATH)
        raw_data = loader.load_data()

        processor = DataProcessor(raw_data)
        filtered_data = processor.filter_by_dates(TARGET_DATES)

        target_dates_dt = [datetime.strptime(d, '%d.%m.%Y') for d in TARGET_DATES]
        visualizer = BoxPlotVisualizer(filtered_data, target_dates_dt)
        visualizer.visualize()

    except Exception as e:
        print(f"Произошла ошибка: {str(e)}")

if __name__ == "__main__":
    main()