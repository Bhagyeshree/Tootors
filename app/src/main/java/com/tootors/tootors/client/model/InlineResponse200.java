package com.tootors.tootors.client.model;

import com.tootors.tootors.client.model.Tootor;
import java.math.BigDecimal;
import java.util.*;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class InlineResponse200  {
  
  @SerializedName("results")
  private List<Tootor> results = null;
  @SerializedName("num_results")
  private BigDecimal numResults = null;
  @SerializedName("status")
  private String status = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public List<Tootor> getResults() {
    return results;
  }
  public void setResults(List<Tootor> results) {
    this.results = results;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public BigDecimal getNumResults() {
    return numResults;
  }
  public void setNumResults(BigDecimal numResults) {
    this.numResults = numResults;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return (results == null ? inlineResponse200.results == null : results.equals(inlineResponse200.results)) &&
        (numResults == null ? inlineResponse200.numResults == null : numResults.equals(inlineResponse200.numResults)) &&
        (status == null ? inlineResponse200.status == null : status.equals(inlineResponse200.status));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (results == null ? 0: results.hashCode());
    result = 31 * result + (numResults == null ? 0: numResults.hashCode());
    result = 31 * result + (status == null ? 0: status.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("  results: ").append(results).append("\n");
    sb.append("  numResults: ").append(numResults).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
