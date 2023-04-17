package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.ui.ResultView;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RacingCarTest {

    @DisplayName("random 값이 4이상이면 전진한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:false", "1:false", "2:false", "3:false",
            "4:true", "5:true", "6:true", "7:true", "8:true", "9:true"}, delimiter = ':')
    void move(int randomNumber, boolean result) {
        //given
        RacingCar racingCar = new RacingCar(3);

        //when
        boolean moveYn = racingCar.isCarMovable(randomNumber);

        //then
        assertThat(moveYn).isEqualTo(result);
    }

    @DisplayName("자동차의 출력 상태값 만들기(1회 전진마다 -추가")
    @ParameterizedTest
    @CsvSource(value = {"0:''", "1:'-'", "2:'--'", "3:'---'", "4:'----'", "5:'-----'"}, delimiter = ':')
    void makeCarMoveStateLine(int moveCount, String result) {
        //given
        ResultView resultView = new ResultView();

        //when
        String carMoveStateLine = resultView.makeCarMoveStateLine(moveCount);

        //then
        assertThat(carMoveStateLine).isEqualTo(result);
    }

    @DisplayName("자동차의 이동 횟수 리스트 만들기")
    @Test
    void makeMoveCounts() {
        //given
        List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6);

        //when
        List<Integer> moveCounts = new RacingCar(numbers.size()).makeMoveCounts(numbers);

        //then
        assertThat(moveCounts).containsExactly(0, 0, 1, 1, 1);
    }

    @DisplayName("자동차 이름 5자 초과시 예외 처리")
    @Test
    void carNameLengthException() {
        assertThatThrownBy(() -> {
            new RacingCar("hoyeon");
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("자동차 이름은 5자를 초과할 수 없습니다.");
    }

    @DisplayName("우승자 판별")
    @Test
    void getWinnersNames() {
        //given
        RacingCar racingCar = new RacingCar("pobi,crong,honux");

        //when
        racingCar.makeMoveCounts(Arrays.asList(1, 4, 5));
        List<String> winnerNames = racingCar.getWinnerNames();

        //then
        assertThat(winnerNames).containsExactly("crong", "honux");
    }
}
