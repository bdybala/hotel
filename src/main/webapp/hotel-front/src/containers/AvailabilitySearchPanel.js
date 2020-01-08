import React, {Component} from 'react';
import {ControlLabel, Form, FormControl} from "react-bootstrap";

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

  handleFromChange(e) {
    this.setState({from: e.target.value}, this.checkForm);
  }

  handleUpToChange(e) {
    this.setState({upTo: e.target.value}, this.checkForm);
  }

  handleCapacityChange(e) {
    this.setState({capacity: e.target.value}, this.checkForm);

  }

  handleSearch() {
    this.props.updateSearchFields(this.state.from, this.state.upTo,
        this.state.capacity);
  }

  checkForm() {
    if (this.state.from.trim() === '' || this.state.upTo.trim() === ''
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
            <FormControl value={this.state.from}
                         onChange={this.handleFromChange}
                         type="text"
                         placeholder="2019-02-02"/>

            <ControlLabel> Do </ControlLabel>{'  '}
            <FormControl value={this.state.upTo}
                         onChange={this.handleUpToChange} type="text"
                         placeholder="2019-02-02"/>

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