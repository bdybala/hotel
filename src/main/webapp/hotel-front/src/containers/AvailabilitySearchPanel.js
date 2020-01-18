import React, {Component} from 'react';
import {ControlLabel, Form, FormControl} from "react-bootstrap";
import DayPickerInput from 'react-day-picker/DayPickerInput';

import 'react-day-picker/lib/style.css';

class AvailabilitySearchPanel extends Component {
  constructor(props) {
    super(props);

    this.handleSearch = this.handleSearch.bind(this);
    this.handleFromChange = this.handleFromChange.bind(this);
    this.handleUpToChange = this.handleUpToChange.bind(this);
    this.handleCapacityChange = this.handleCapacityChange.bind(this);

    this.state = {
      from: '',
      upTo: '',
      capacity: '',
      formValid: false,
    }
  }

  handleFromChange(day) {
    this.setState({from: day}, this.checkForm);
  }

  handleUpToChange(day) {
    this.setState({upTo: day}, this.checkForm);
  }

  handleCapacityChange(e) {
    this.setState({capacity: e.target.value}, this.checkForm);

  }

  handleSearch() {
    this.props.updateSearchFields(this.state.from, this.state.upTo,
        this.state.capacity);
  }

  checkForm() {
    if (this.state.from === null || this.state.upTo === null
        || this.state.capacity.trim() === '') {
      this.setState({formValid: false});
    } else {
      this.setState({formValid: true});
    }
  }

  render() {
    return (
        <div className="AvailabilitySearchPanel">
          <Form inline>

            <ControlLabel> Od </ControlLabel>{'  '}
            <DayPickerInput
                onDayChange={this.handleFromChange}
                dayPickerProps={{
                  month: new Date(),
                  showWeekNumbers: true,
                  todayButton: 'Dzisiaj',
                }}
            />
            <ControlLabel> Do </ControlLabel>{'  '}
            <DayPickerInput
                onDayChange={this.handleUpToChange}
                dayPickerProps={{
                  month: new Date(),
                  showWeekNumbers: true,
                  todayButton: 'Dzisiaj',
                }}
            />
            <ControlLabel> Liczba gości </ControlLabel>{'  '}
            <FormControl value={this.state.capacity}
                         onChange={this.handleCapacityChange} type="text"
                         placeholder="5"/>

            <button type="button" className="btn btn-primary"
                    onClick={this.handleSearch}
                    disabled={!this.state.formValid}>
              Szukaj
            </button>
          </Form>
        </div>
    )
  }

}

export default AvailabilitySearchPanel;