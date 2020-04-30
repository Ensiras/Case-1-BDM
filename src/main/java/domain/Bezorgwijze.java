package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public enum Bezorgwijze {
    AFHALEN_MAGAZIJN,
    AFHALEN_THUIS,
    VERSTUREN_VOORBET,
    VERSTUREN_REMBOURS;

}
